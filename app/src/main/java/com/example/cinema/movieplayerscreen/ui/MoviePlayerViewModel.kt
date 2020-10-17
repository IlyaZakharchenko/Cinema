package com.example.cinema.movieplayerscreen.ui

import com.example.cinema.base.BaseViewModel
import com.example.cinema.base.Event
import com.example.cinema.movieplayerscreen.STATUS
import com.example.cinema.movieplayerscreen.ViewState

class MoviePlayerViewModel : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(STATUS.LOAD)

    override fun reduce(
        event: Event,
        previousState: ViewState
    ): ViewState? {
        when (event) {

        }
        return null
    }
}