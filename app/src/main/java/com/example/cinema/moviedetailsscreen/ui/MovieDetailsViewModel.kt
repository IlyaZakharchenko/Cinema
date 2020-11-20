package com.example.cinema.moviedetailsscreen.ui

import com.example.cinema.MoviePlayerScreen
import com.example.cinema.base.BaseViewModel
import com.example.cinema.base.Event
import com.example.cinema.model.MovieModel
import com.example.cinema.moviedetailsscreen.STATUS
import com.example.cinema.moviedetailsscreen.UiEvent
import com.example.cinema.moviedetailsscreen.ViewState
import com.google.android.exoplayer2.SimpleExoPlayer
import ru.terrakok.cicerone.Router

class MovieDetailsViewModel(
    private val router: Router,
    private val movie: MovieModel
) : BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(STATUS.CONTENT, movie, false)

    override fun reduce(
        event: Event,
        previousState: ViewState
    ): ViewState? {
        when (event) {
            UiEvent.OnPlayClicked -> {
                return previousState.copy(isPlayerShown = true)
            }

            UiEvent.OnFullscreenClicked -> {
                router.navigateTo(MoviePlayerScreen())
            }
        }
        return null
    }
}