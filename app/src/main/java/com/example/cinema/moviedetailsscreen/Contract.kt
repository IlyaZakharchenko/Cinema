package com.example.cinema.moviedetailsscreen

import com.example.cinema.base.Event
import com.example.cinema.model.MovieModel

data class ViewState(
    val status: STATUS,
    val movieModel: MovieModel,
    val isPlayerShown: Boolean
)

sealed class UiEvent : Event {
    object OnPlayClicked: UiEvent()
    object OnFullscreenClicked: UiEvent()
}

sealed class DataEvent : Event

enum class STATUS {
    LOAD,
    ERROR,
    CONTENT
}