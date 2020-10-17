package com.example.cinema.moviedetailsscreen

import com.example.cinema.CINEMA_QUALIFIER
import com.example.cinema.moviedetailsscreen.ui.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieDetailsModule = module {

    viewModel<MovieDetailsViewModel> {
        MovieDetailsViewModel(get(named(CINEMA_QUALIFIER)))
    }
}