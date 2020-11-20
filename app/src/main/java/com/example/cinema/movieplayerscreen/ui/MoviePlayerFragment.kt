package com.example.cinema.movieplayerscreen.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.OrientationEventListener
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.cinema.R
import com.example.cinema.base.ViewStateObserver
import com.example.cinema.moviedetailsscreen.PLAYER_SCOPE
import com.example.cinema.movieplayerscreen.STATUS
import com.example.cinema.movieplayerscreen.UiEvent
import com.example.cinema.movieplayerscreen.ViewState
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.android.synthetic.main.exo_styled_player_control_view.*
import kotlinx.android.synthetic.main.fragment_movie_player.*
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviePlayerFragment : ViewStateObserver<ViewState>, Fragment(R.layout.fragment_movie_player) {

    override val viewModel: MoviePlayerViewModel by viewModel()

    private val playerScope = getKoin().getScope(PLAYER_SCOPE)
    private val player: SimpleExoPlayer = playerScope.get()

    companion object {
        fun newInstance() = MoviePlayerFragment()
    }
    override fun onStart() {
        super.onStart()

        retainInstance = true
        hideSystemUi()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exo_fullscreen.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.exo_controls_fullscreen_exit,
                requireContext().theme
            )
        )
        exo_fullscreen.visibility = View.VISIBLE
        exo_fullscreen.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnFullscreenExit)
        }
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
    }

    override fun onDestroy() {
        showSystemUi()
        player.stop()

        super.onDestroy()
    }

    override fun render(viewState: ViewState) {
        when (viewState.status) {
            STATUS.LOAD -> {

            }
            STATUS.ERROR -> {

            }
            STATUS.CONTENT -> {
                playerViewFullscreen.player = player.apply {
                    prepare()
                    if (playWhenReady) {
                        play()
                    }
                }
            }
        }
    }

    private fun hideSystemUi() {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE

        (activity as AppCompatActivity).supportActionBar?.hide()
        @Suppress("DEPRECATION")
        (activity as AppCompatActivity).let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                it.window.insetsController?.hide(WindowInsets.Type.statusBars())
                it.window.insetsController?.hide(WindowInsets.Type.navigationBars())
                it.window.insetsController?.hide(WindowInsets.Type.systemBars())
            } else {
                it.window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
                it.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN)
            }
        }
    }

    private fun showSystemUi() {
        (activity as AppCompatActivity).supportActionBar?.show()
        @Suppress("DEPRECATION")
        (activity as AppCompatActivity).let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                it.window.insetsController?.show(WindowInsets.Type.statusBars())
                it.window.insetsController?.show(WindowInsets.Type.navigationBars())
                it.window.insetsController?.show(WindowInsets.Type.systemBars())
            } else {
                it.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            }
        }
    }

}