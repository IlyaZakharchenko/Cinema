package com.example.cinema.movieplayerscreen

import com.example.cinema.base.Event

data class ViewState(
    val status: STATUS
)

sealed class UiEvent : Event

sealed class DataEvent : Event

enum class STATUS {
    LOAD,
    ERROR,
    CONTENT
}