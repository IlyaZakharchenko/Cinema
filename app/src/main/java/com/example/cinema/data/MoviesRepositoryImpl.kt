package com.example.cinema.data

import com.example.cinema.data.remote.MoviesRemoteSource
import com.example.cinema.model.PageModel

class MoviesRepositoryImpl(private val remoteSource: MoviesRemoteSource) : MoviesRepository {
    override suspend fun getMovies(): PageModel = remoteSource.getMovies().mapToUi()
}