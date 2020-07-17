package ru.napoleonit.bottomsheettest

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ru.napoleonit.bottomsheettest.di.ViewModelAssistedFactory
import ru.napoleonit.bottomsheettest.domain.usecase.stories.GetClipsUseCase
import ru.napoleonit.bottomsheettest.presentation.viewmodel.useCaseLiveData
import javax.inject.Inject

class MainViewModel(private val getClipsUseCase: GetClipsUseCase, private val savedStateHandle: SavedStateHandle) :
    ViewModel() {

    val clips by useCaseLiveData(params = Unit, useCase = getClipsUseCase)

    class MainViewModelFactory @Inject constructor(private val getStoriesUseCase: GetClipsUseCase) :
        ViewModelAssistedFactory<MainViewModel> {
        override fun create(handle: SavedStateHandle): MainViewModel = MainViewModel(getStoriesUseCase, handle)
    }
}