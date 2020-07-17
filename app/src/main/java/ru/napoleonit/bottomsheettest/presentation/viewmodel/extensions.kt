package ru.napoleonit.bottomsheettest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.napoleonit.bottomsheettest.domain.usecase.UseCase
import kotlin.properties.ReadOnlyProperty


fun <C, P> ViewModel.useCaseLiveData(
    initialValue: C? = null,
    params: P,
    useCase: UseCase<C, P>
): ReadOnlyProperty<ViewModel, LiveData<State<C>>> {
    return StatefulLiveData(initialValue, params, useCase::invoke)
}

