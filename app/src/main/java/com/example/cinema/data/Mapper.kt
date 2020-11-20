package com.example.cinema.data

import com.example.cinema.data.remote.model.GenreRemoteModel
import com.example.cinema.data.remote.model.MovieRemoteModel
import com.example.cinema.data.remote.model.PageRemoteModel
import com.example.cinema.model.MovieModel
import com.example.cinema.model.PageModel

fun PageRemoteModel.mapToUi(): PageModel =
    PageModel(page, results.map(MovieRemoteModel::mapToUi), totalPages, totalResults)

fun MovieRemoteModel.mapToUi(): MovieModel =
    MovieModel(
        isAdult,
        genres.map(GenreRemoteModel::mapToUi),
        id,
        originalLanguage,
        originalTitle,
        overview,
        releaseDate,
        posterPath,
        popularity,
        title,
        videoPath,
        averageVote,
        voteCount
    )

fun GenreRemoteModel.mapToUi(): String = name