package com.example.cinema.movielistscreen.ui

import androidx.lifecycle.viewModelScope
import com.example.cinema.MovieDetailsScreen
import com.example.cinema.R
import com.example.cinema.base.BaseViewModel
import com.example.cinema.base.Event
import com.example.cinema.movielistscreen.DataEvent
import com.example.cinema.movielistscreen.STATUS
import com.example.cinema.movielistscreen.UiEvent
import com.example.cinema.movielistscreen.ViewState
import com.example.cinema.movielistscreen.data.MovieListInteractor
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import java.net.UnknownHostException

class MovieListViewModel(private val interactor: MovieListInteractor, private val router: Router) :
    BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(STATUS.LOAD, emptyList(), null)

    override fun reduce(
        event: Event,
        previousState: ViewState
    ): ViewState? {
        when (event) {
            is DataEvent.OnRequestMovies -> {
                viewModelScope.launch {
                    interactor.getMovies().fold(
                        {
                            when (it) {
                                is UnknownHostException -> processUiEvent(DataEvent.OnError(R.string.connection_error))
                                else -> processUiEvent(DataEvent.OnError(R.string.data_load_error))
                            }
                        }, {
                            processDataEvent(DataEvent.OnMoviesRequestSuccess(it.results))
                        }
                    )
                }
            }

            is DataEvent.OnMoviesRequestSuccess -> {
                return previousState.copy(status = STATUS.CONTENT, movieList = event.movieList)
            }

            is UiEvent.OnMovieClick -> {
                router.navigateTo(MovieDetailsScreen(event.movieModel))
            }

            is UiEvent.OnRetry -> {
                processDataEvent(DataEvent.OnRequestMovies)
            }

            is DataEvent.OnError -> {
                return previousState.copy(status = STATUS.ERROR, errorMessage = event.errorMessage)
            }
        }
        return previousState.copy(status = STATUS.LOAD)
    }
}