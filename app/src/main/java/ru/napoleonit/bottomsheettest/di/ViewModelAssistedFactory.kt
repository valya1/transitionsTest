package ru.napoleonit.bottomsheettest.di

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

interface ViewModelAssistedFactory<out T : ViewModel> {
    fun create(handle: SavedStateHandle): T
}