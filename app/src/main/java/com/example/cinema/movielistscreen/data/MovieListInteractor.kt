package com.example.cinema.movielistscreen.data

import com.example.cinema.attempt
import com.example.cinema.data.MoviesRepository

class MovieListInteractor(private val repository: MoviesRepository) {
    suspend fun getMovies() = attempt { repository.getMovies() }
}