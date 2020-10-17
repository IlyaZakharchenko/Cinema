package com.example.cinema.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieRemoteModel(
    @SerializedName("adult")
    val isAdult: Boolean,
    @SerializedName("genres")
    val genres: List<GenreRemoteModel>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val videoPath: String,
    @SerializedName("vote_average")
    val averageVote: Double,
    @SerializedName("voteCount")
    val voteCount: Int
)