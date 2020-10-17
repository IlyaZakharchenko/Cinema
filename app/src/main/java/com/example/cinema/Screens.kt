package com.example.cinema

import androidx.fragment.app.Fragment
import com.example.cinema.moviedetailsscreen.ui.MovieDetailsFragment
import com.example.cinema.movielistscreen.ui.MovieListFragment
import com.example.cinema.movieplayerscreen.ui.MoviePlayerFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MovieListScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? = MovieListFragment.newInstance()
}

class MovieDetailsScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? = MovieDetailsFragment.newInstance()
}

class MoviePlayerScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? = MoviePlayerFragment.newInstance()
}