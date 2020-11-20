package com.example.cinema.movielistscreen

import com.example.cinema.CINEMA_QUALIFIER
import com.example.cinema.movielistscreen.ui.MovieListViewModel
import com.google.android.exoplayer2.SimpleExoPlayer
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieListModule = module {

    viewModel<MovieListViewModel> {
        MovieListViewModel(get(), get(named(CINEMA_QUALIFIER)))
    }

}