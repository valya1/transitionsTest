package ru.napoleonit.bottomsheettest

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class DissapearingAppbarScrollBehavior(context: Context, attributeSet: AttributeSet) :
    AppBarLayout.ScrollingViewBehavior(context, attributeSet) {

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {

        val appbarHeight = (dependency as AppBarLayout).height
        val appbarBottom = dependency.bottom
        val relativeScroll = if (appbarHeight > 0) appbarBottom / appbarHeight.toFloat() else 1.0f
        dependency.alpha = relativeScroll
        return super.onDependentViewChanged(parent, child, dependency)
    }

}