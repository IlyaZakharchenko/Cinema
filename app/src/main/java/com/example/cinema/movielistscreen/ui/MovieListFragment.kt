package com.example.cinema.movielistscreen.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema.R
import com.example.cinema.base.BaseFragment
import com.example.cinema.movielistscreen.STATUS
import com.example.cinema.movielistscreen.UiEvent
import com.example.cinema.movielistscreen.ViewState
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(R.layout.fragment_movie_list), BaseFragment<ViewState> {

    override val viewModel: MovieListViewModel by viewModel()

    private val adapter = ListDelegationAdapter(
        moviesAdapter { viewModel.processUiEvent(UiEvent.OnMovieClick(it)) }
    )

    companion object {
        fun newInstance(): MovieListFragment {
            val args = Bundle()

            val fragment = MovieListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMovies.layoutManager = LinearLayoutManager(requireContext())
        rvMovies.adapter = adapter
    }


    override fun render(viewState: ViewState) {
        when (viewState.status) {
            STATUS.LOAD -> {
            }
            STATUS.ERROR -> {
            }
            STATUS.CONTENT -> {
                adapter.items = viewState.movieList
                adapter.notifyDataSetChanged()
            }
        }
    }
}