package com.example.cinema.movieplayerscreen.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cinema.R
import com.example.cinema.base.BaseFragment
import com.example.cinema.movieplayerscreen.STATUS
import com.example.cinema.movieplayerscreen.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviePlayerFragment : Fragment(R.layout.fragment_movie_player), BaseFragment<ViewState> {

    override val viewModel: MoviePlayerViewModel by viewModel()

    companion object {
        fun newInstance(): MoviePlayerFragment {
            val args = Bundle()

            val fragment = MoviePlayerFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun render(viewState: ViewState) {
        when (viewState.status) {
            STATUS.LOAD -> {
            }
            STATUS.ERROR -> {
            }
            STATUS.CONTENT -> {
            }
        }
    }
}