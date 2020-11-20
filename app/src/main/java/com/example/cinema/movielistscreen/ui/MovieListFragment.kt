package com.example.cinema.movielistscreen.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinema.R
import com.example.cinema.base.ViewStateObserver
import com.example.cinema.movielistscreen.STATUS
import com.example.cinema.movielistscreen.UiEvent
import com.example.cinema.movielistscreen.ViewState
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(R.layout.fragment_movie_list), ViewStateObserver<ViewState> {

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
        rvMovies.layoutManager = GridLayoutManager(requireContext(), 2)
        rvMovies.adapter = adapter
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
    }

    private fun showErrorDialog(message: String) {
        AlertDialog
            .Builder(requireContext())
            .setMessage(message)
            .setTitle(getString(R.string.dialog_title_error))
            .setNeutralButton(getString(R.string.dialog_try_again)) {
                    _, _ -> viewModel.processUiEvent(UiEvent.OnRetry)
            }
            .create()
            .show()
    }


    override fun render(viewState: ViewState) {
        when (viewState.status) {
            STATUS.LOAD -> {
                showProgressBar(true)
            }
            STATUS.ERROR -> {
                showProgressBar(false)
                showErrorDialog(viewState.errorMessage?.let { getString(it) } ?: "")
            }
            STATUS.CONTENT -> {
                showProgressBar(false)
                adapter.items = viewState.movieList
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun showProgressBar(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
        rvMovies.visibility = if (isVisible) View.GONE else View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        viewModel.processUiEvent(UiEvent.OnRetry)
    }
}