package com.example.cinema.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<VIEW_STATE> : ViewModel() {

    val viewState: MutableLiveData<VIEW_STATE> by lazy { MutableLiveData(initialViewState()) }

    abstract fun initialViewState(): VIEW_STATE

    abstract fun reduce(event: Event, previousState: VIEW_STATE): VIEW_STATE?

    fun processUiEvent(uiEvent: Event) {
        updateState(uiEvent)
    }

    protected fun processDataEvent(dataEvent: Event) {
        updateState(dataEvent)
    }

    private fun updateState(event: Event) {
        val newViewState = reduce(event, viewState.value ?: initialViewState())
        if (newViewState != null && newViewState != viewState.value) {
            viewState.value = newViewState
        }
    }
}