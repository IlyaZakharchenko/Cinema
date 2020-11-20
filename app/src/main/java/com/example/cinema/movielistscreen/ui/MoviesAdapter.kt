package com.example.cinema.movielistscreen.ui

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.core.content.res.ResourcesCompat
import com.example.cinema.R
import com.example.cinema.base.Item
import com.example.cinema.model.MovieModel
import com.example.cinema.setImage
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.item_movie.view.*

fun moviesAdapter(onClick: (MovieModel) -> Unit): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<MovieModel, Item>(
        R.layout.item_movie
    ) {
        itemView.setOnClickListener { onClick(item) }
        bind {
            with(itemView) {
                ivPoster.setImage(item.posterPath)
                tvTitle.text = item.title
                tvRating.text = item.averageVote.toString()
                val color = when(item.averageVote) {
                    in 0.0..3.9 -> {
                        ResourcesCompat.getColor(resources, R.color.colorRatingRed, context.theme)
                    }
                    in 4.0..6.9 -> {
                        ResourcesCompat.getColor(resources, R.color.colorRatingOrange, context.theme)
                    }
                    else -> {
                        ResourcesCompat.getColor(resources, R.color.colorRatingGreen, context.theme)
                    }
                }
                tvRating.background.colorFilter = PorterDuffColorFilter(
                    color, PorterDuff.Mode.SRC_IN
                )
                if (item.genres.size > 1) {
                    tvGenre.text = getString(
                        R.string.format_genres, item.genres.first(),
                        item.genres.last()
                    )
                } else {
                    tvGenre.text = item.genres.first()
                }
            }

        }
    }