package com.example.cinema.data

import com.example.cinema.model.PageModel

interface MoviesRepository {
    suspend fun getMovies(): PageModel
}