package com.example.cinema.moviedetailsscreen.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.OrientationEventListener
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.cinema.R
import com.example.cinema.base.ViewStateObserver
import com.example.cinema.model.MovieModel
import com.example.cinema.moviedetailsscreen.PLAYER_SCOPE
import com.example.cinema.moviedetailsscreen.STATUS
import com.example.cinema.moviedetailsscreen.UiEvent
import com.example.cinema.moviedetailsscreen.ViewState
import com.example.cinema.setImage
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.exo_styled_player_control_view.*
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.item_movie.ivPoster
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named


class MovieDetailsFragment : ViewStateObserver<ViewState>,
    Fragment(R.layout.fragment_movie_details) {

    override val viewModel: MovieDetailsViewModel by viewModel {
        parametersOf(arguments?.get(MOVIE_MODEL_KEY))
    }
    private val playerScope by lazy {
        getKoin().getOrCreateScope(
            PLAYER_SCOPE,
            named(PLAYER_SCOPE)
        )
    }
    private lateinit var player: SimpleExoPlayer
    private var isPlayerShown = false
    private lateinit var orientationListener: OrientationEventListener

    companion object {
        private const val MOVIE_MODEL_KEY = "MOVIE_MODEL_KEY"
        fun newInstance(movieModel: MovieModel): MovieDetailsFragment {
            val args = Bundle()
            args.putParcelable(MOVIE_MODEL_KEY, movieModel)
            val fragment = MovieDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initOrientationListener()
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPlayer()
        btnPlay.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnPlayClicked)
        }
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
    }

    override fun render(viewState: ViewState) {
        when (viewState.status) {
            STATUS.LOAD -> {
            }
            STATUS.ERROR -> {
            }
            STATUS.CONTENT -> {
                isPlayerShown = viewState.isPlayerShown
                if (isPlayerShown) {
                    showPlayer()
                } else {
                    showMovieInfo(viewState.movieModel)
                }
            }
        }
    }

    private fun showPlayer() {
        playerView.visibility = View.VISIBLE
        if (player.playWhenReady) {
            player.play()
        }
        ivPoster.visibility = View.GONE
        gradient.visibility = View.GONE
        toolbar.visibility = View.GONE
        btnPlay.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.hide()
        if (isPlayerShown) {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
        } else {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER
        }
        if (orientationListener.canDetectOrientation()) {
            orientationListener.enable()
        }
    }

    private fun initOrientationListener() {
        orientationListener = object : OrientationEventListener(
            requireContext()
        ) {
            override fun onOrientationChanged(orientation: Int) {
                if ((orientation == 90 || orientation == 270) && isPlayerShown) {
                    viewModel.processUiEvent(UiEvent.OnFullscreenClicked)
                }
            }
        }
    }

    private fun initPlayer() {
        player = playerScope.get()
        playerView.let {
            it.player = player
            it.visibility = View.VISIBLE
            exo_fullscreen.visibility = View.VISIBLE
            exo_fullscreen.setOnClickListener {
                viewModel.processUiEvent(UiEvent.OnFullscreenClicked)
            }
        }
    }

    override fun onStop() {
        (activity as AppCompatActivity).supportActionBar?.show()
        orientationListener.disable()
        super.onStop()
    }

    override fun onDestroy() {
        player.release()
        playerScope.close()
        super.onDestroy()
    }

    private fun showMovieInfo(movieModel: MovieModel) {
        movieModel.let {
            player.setMediaItem(MediaItem.fromUri(it.videoPath))
            player.prepare()
            ivPoster.setImage(it.posterPath)
            toolbar.title = it.originalTitle
            tvOverview.text = it.overview

            tvRating.text = it.averageVote.toString()
            val color = when (it.averageVote) {
                in 0.0..3.9 -> {
                    ResourcesCompat.getColor(
                        resources,
                        R.color.colorRatingRed,
                        requireContext().theme
                    )
                }
                in 4.0..6.9 -> {
                    ResourcesCompat.getColor(
                        resources,
                        R.color.colorRatingOrange,
                        requireContext().theme
                    )
                }
                else -> {
                    ResourcesCompat.getColor(
                        resources,
                        R.color.colorRatingGreen,
                        requireContext().theme
                    )
                }
            }
            tvRating.setTextColor(color)
            ratingBar.rating = it.averageVote.toFloat()
            tvVotes.text = it.voteCount.toString()

            ivPegi.visibility = if (it.isAdult) View.VISIBLE else View.INVISIBLE

            for (genre in it.genres) {
                val chip = Chip(genreChips.context).apply {
                    text = genre
                }
                genreChips.addView(chip)
            }
        }
    }
}