package com.example.cinema.moviedetailsscreen

import com.example.cinema.CINEMA_QUALIFIER
import com.example.cinema.model.MovieModel
import com.example.cinema.moviedetailsscreen.ui.MovieDetailsViewModel
import com.google.android.exoplayer2.SimpleExoPlayer
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val PLAYER_SCOPE = "PLAYER_SCOPE"
val movieDetailsModule = module {

    viewModel<MovieDetailsViewModel> { (movie: MovieModel) ->
        MovieDetailsViewModel(get(named(CINEMA_QUALIFIER)), movie)
    }

    scope(named(PLAYER_SCOPE)) {
        scoped {
            SimpleExoPlayer
                .Builder(androidContext())
                .build()
        }
    }
}