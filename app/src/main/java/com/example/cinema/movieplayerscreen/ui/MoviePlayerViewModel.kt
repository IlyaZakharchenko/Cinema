package com.example.cinema.movieplayerscreen.ui

import com.example.cinema.base.BaseViewModel
import com.example.cinema.base.Event
import com.example.cinema.movieplayerscreen.STATUS
import com.example.cinema.movieplayerscreen.UiEvent
import com.example.cinema.movieplayerscreen.ViewState
import com.google.android.exoplayer2.SimpleExoPlayer
import ru.terrakok.cicerone.Router

class MoviePlayerViewModel(private val router: Router) :
    BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(STATUS.CONTENT)

    override fun reduce(
        event: Event,
        previousState: ViewState
    ): ViewState? {
        when (event) {
            UiEvent.OnFullscreenExit -> {
                router.exit()
            }
        }
        return null
    }
}