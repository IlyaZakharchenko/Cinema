package com.example.cinema.moviedetailsscreen.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.cinema.R
import com.example.cinema.base.BaseFragment
import com.example.cinema.moviedetailsscreen.STATUS
import com.example.cinema.moviedetailsscreen.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : BaseFragment<ViewState>, Fragment(R.layout.fragment_movie_details) {

    override val viewModel: MovieDetailsViewModel by viewModel()

    companion object {
        fun newInstance(): MovieDetailsFragment {
            val args = Bundle()

            val fragment = MovieDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
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