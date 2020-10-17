package com.example.cinema.data.remote.model

import com.google.gson.annotations.SerializedName

data class PageRemoteModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieRemoteModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)