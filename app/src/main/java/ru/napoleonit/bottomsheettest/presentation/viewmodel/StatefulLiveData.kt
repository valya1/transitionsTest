package ru.napoleonit.bottomsheettest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class StatefulLiveData<C, P>(
    private val initial: C? = null,
    private val param: P,
    private val block: suspend (P) -> C
) : ReadOnlyProperty<ViewModel, LiveData<State<C>>> {

    private var backingLiveData: LiveData<State<C>>? = null

    override fun getValue(thisRef: ViewModel, property: KProperty<*>): LiveData<State<C>> {

        backingLiveData?.let { return it }

        val liveData = MutableLiveData<State<C>>()
        thisRef.viewModelScope.launch {
            liveData.value = State(isLoading = true, value = initial)
            val next = block(param)
            liveData.value = State(isLoading = false, value = next)
        }
        backingLiveData = liveData
        return liveData
    }
}