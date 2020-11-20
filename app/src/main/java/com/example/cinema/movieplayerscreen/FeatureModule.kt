package com.example.cinema.movieplayerscreen

import com.example.cinema.CINEMA_QUALIFIER
import com.example.cinema.movieplayerscreen.ui.MoviePlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moviePlayerModule = module {

    viewModel<MoviePlayerViewModel> {
        MoviePlayerViewModel(get(named(CINEMA_QUALIFIER)))
    }
}