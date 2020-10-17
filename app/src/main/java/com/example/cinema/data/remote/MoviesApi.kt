package com.example.cinema.data.remote

import com.example.cinema.data.remote.model.PageRemoteModel
import retrofit2.http.GET

interface MoviesApi {

    @GET(value = "movies.json")
    suspend fun getMovies(): PageRemoteModel
}