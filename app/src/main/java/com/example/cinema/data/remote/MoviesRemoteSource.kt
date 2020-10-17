package com.example.cinema.data.remote

class MoviesRemoteSource(private val moviesApi: MoviesApi) {
    suspend fun getMovies() = moviesApi.getMovies()
}