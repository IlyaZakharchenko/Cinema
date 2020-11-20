package com.example.cinema.movieplayerscreen

import com.example.cinema.base.Event
import com.google.android.exoplayer2.SimpleExoPlayer

data class ViewState(
    val status: STATUS
)

sealed class UiEvent : Event {
    object OnFullscreenExit: UiEvent()
}

sealed class DataEvent : Event

enum class STATUS {
    LOAD,
    ERROR,
    CONTENT
}