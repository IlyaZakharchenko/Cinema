package com.example.cinema.model

data class PageModel(
    val page: Int,
    val results: List<MovieModel>,
    val totalPages: Int,
    val totalResults: Int
)