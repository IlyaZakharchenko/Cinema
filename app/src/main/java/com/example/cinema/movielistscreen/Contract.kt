package com.example.cinema.movielistscreen

import com.example.cinema.base.Event
import com.example.cinema.model.MovieModel

data class ViewState(
    val status: STATUS,
    val movieList: List<MovieModel>
)

sealed class UiEvent : Event {
    data class OnMovieClick(val position: Int) : UiEvent()
}

sealed class DataEvent : Event {
    object RequestMovies : DataEvent()
    data class OnMoviesRequestSuccess(val movieList: List<MovieModel>) : DataEvent()
}

enum class STATUS {
    LOAD,
    ERROR,
    CONTENT
}