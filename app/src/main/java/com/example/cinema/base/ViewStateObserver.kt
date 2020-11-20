package com.example.cinema.base

interface ViewStateObserver<VIEW_STATE> {
    val viewModel: BaseViewModel<VIEW_STATE>

    fun render(viewState: VIEW_STATE)
}