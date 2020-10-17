package com.example.cinema.movielistscreen.ui

import androidx.lifecycle.viewModelScope
import com.example.cinema.MovieDetailsScreen
import com.example.cinema.base.BaseViewModel
import com.example.cinema.base.Event
import com.example.cinema.movielistscreen.DataEvent
import com.example.cinema.movielistscreen.STATUS
import com.example.cinema.movielistscreen.UiEvent
import com.example.cinema.movielistscreen.ViewState
import com.example.cinema.movielistscreen.data.MovieListInteractor
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

class MovieListViewModel(private val interactor: MovieListInteractor, private val router: Router) :
    BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(STATUS.LOAD, emptyList())

    init {
        processDataEvent(DataEvent.RequestMovies)
    }

    override fun reduce(
        event: Event,
        previousState: ViewState
    ): ViewState? {
        when (event) {
            is DataEvent.RequestMovies -> {
                viewModelScope.launch {
                    interactor.getMovies().fold(
                        { throw it },
                        { processDataEvent(DataEvent.OnMoviesRequestSuccess(it.results)) }
                    )
                }
            }

            is DataEvent.OnMoviesRequestSuccess -> {
                return previousState.copy(status = STATUS.CONTENT, movieList = event.movieList)
            }

            is UiEvent.OnMovieClick -> {
                router.navigateTo(MovieDetailsScreen())
            }
        }
        return null
    }
}