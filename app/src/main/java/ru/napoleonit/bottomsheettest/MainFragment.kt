package ru.napoleonit.bottomsheettest

import android.os.Bundle
import android.transition.ChangeTransform
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.transition.doOnStart
import androidx.core.view.ViewCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_bottomsheet_test.*
import ru.napoleonit.bottomsheettest.di.GenericViewModelFactory
import ru.napoleonit.bottomsheettest.domain.entities.Clip
import javax.inject.Inject

class MainFragment : DaggerFragment(R.layout.fragment_bottomsheet_test) {

    @Inject
    lateinit var viewModelFactory: MainViewModel.MainViewModelFactory

    private var selectedClipView: View? = null

    private val viewModel: MainViewModel by activityViewModels { GenericViewModelFactory(viewModelFactory, this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.clips.observe(viewLifecycleOwner, Observer { (state, clips) ->
            setLoadingState(state)
            if (clips != null) showClips(clips, ::println)
        })

        ViewCompat.setOnApplyWindowInsetsListener(text) { a, b ->
            val insetTop = b.systemWindowInsetTop
            a.updatePadding(top = resources.getDimension(R.dimen.test_padding_top).toInt() + insetTop)
            b
        }
    }

    private fun showClips(clips: List<Clip>, clipClickListener: (Clip) -> Unit) {
        for (clip in clips) {
            val clipView = generateClipView(clip)
            clipView.setOnClickListener {
                clipClickListener.invoke(clip)
                if (clipView != selectedClipView) {
                    animateSelectedClipTransition(clipsContainer, selectedClipView as ViewGroup, clipView as ViewGroup)
                    selectedClipView = clipView
                }
            }
            clipView.measure(0, 0)
            clipView.layoutParams = LinearLayout.LayoutParams(clipView.measuredWidth, clipView.measuredHeight)
            clipsContainer.addView(clipView)
        }
    }

    private fun animateSelectedClipTransition(
        transitionsContainer: ViewGroup,
        removeSelected: ViewGroup,
        setSelected: ViewGroup
    ) {
        val animatingView = removeSelected.findViewWithTag<View>("Animatable View")
        val startWidth = animatingView.layoutParams.width
        val targetWidth = setSelected.width

        val transition = ChangeTransform()
            .setDuration(500)
            .setInterpolator {
                val delta = targetWidth - startWidth
                animatingView.updateLayoutParams {
                    width = (startWidth + delta * it).toInt()
                }
                it
            }
            .apply {
                addListener(doOnStart {
                    removeSelected.ind
                })
            }

        TransitionManager.beginDelayedTransition(transitionsContainer, transition)
        removeSelected.removeView(animatingView)
        setSelected.addView(animatingView, 0, animatingView.layoutParams)
    }

    private fun generateClipView(clip: Clip): View {
        val context = requireContext()
        val clipContainer = FrameLayout(context)
        val clipTextView = layoutInflater.inflate(R.layout.clip_text, clipContainer, false) as TextView
        val clipTextViewLayoutParams =
            FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .apply { this.gravity = Gravity.CENTER }

        clipTextView.apply {
            layoutParams = clipTextViewLayoutParams
            text = clip.title
            measure(0, 0)
        }
        if (clip.isSelected) {

            val selectedClipView = View(context).apply {
                tag = "Animatable View"
                background = context.getDrawable(R.drawable.background_selected_text)
                layoutParams = FrameLayout.LayoutParams(clipTextView.measuredWidth, clipTextView.measuredHeight)
            }
            clipContainer.addView(selectedClipView)
            this.selectedClipView = clipContainer
        }
        clipContainer.addView(clipTextView)
        return clipContainer
    }

    fun setLoadingState(isLoading: Boolean) {
        Toast.makeText(requireContext(), if (isLoading) "Загрузка" else "Загрузка завершена", Toast.LENGTH_SHORT)
            .show()
    }
}



