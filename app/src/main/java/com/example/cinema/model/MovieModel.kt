package com.example.cinema.model

import com.example.cinema.base.Item

data class MovieModel(
    val isAdult: Boolean,
    val genres: List<GenreModel>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String,
    val popularity: Double,
    val title: String,
    val videoPath: String,
    val averageVote: Double,
    val voteCount: Int
) : Item