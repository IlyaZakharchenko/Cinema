package com.example.cinema.moviedetailsscreen.ui

import com.example.cinema.base.BaseViewModel
import com.example.cinema.base.Event
import com.example.cinema.moviedetailsscreen.STATUS
import com.example.cinema.moviedetailsscreen.ViewState
import ru.terrakok.cicerone.Router

class MovieDetailsViewModel(private val router: Router) : BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(STATUS.LOAD, null)

    override fun reduce(
        event: Event,
        previousState: ViewState
    ): ViewState? {
        when (event) {

        }
        return null
    }
}