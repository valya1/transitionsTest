package ru.napoleonit.bottomsheettest.presentation.viewmodel

data class State<T>(
    var isLoading: Boolean = false,
    var value: T?
)