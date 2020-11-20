package com.example.cinema.movielistscreen

import com.example.cinema.base.Event
import com.example.cinema.model.MovieModel

data class ViewState(
    val status: STATUS,
    val movieList: List<MovieModel>,
    val errorMessage: Int?
)

sealed class UiEvent : Event {
    data class OnMovieClick(val movieModel: MovieModel) : UiEvent()
    object OnRetry: UiEvent()
}

sealed class DataEvent : Event {
    object OnRequestMovies : DataEvent()
    data class OnMoviesRequestSuccess(val movieList: List<MovieModel>) : DataEvent()
    data class OnError(val errorMessage: Int?): DataEvent()
}

enum class STATUS {
    LOAD,
    ERROR,
    CONTENT
}