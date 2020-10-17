package com.example.cinema.moviedetailsscreen

import com.example.cinema.base.Event
import com.example.cinema.model.MovieModel

data class ViewState(
    val status: STATUS,
    val movieModel: MovieModel?
)

sealed class UiEvent : Event

sealed class DataEvent : Event

enum class STATUS {
    LOAD,
    ERROR,
    CONTENT
}