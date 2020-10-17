package com.example.cinema.movielistscreen.ui

import com.bumptech.glide.Glide
import com.example.cinema.R
import com.example.cinema.base.Item
import com.example.cinema.model.MovieModel
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.item_movie.view.*

fun moviesAdapter(onClick: (Int) -> Unit): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<MovieModel, Item>(
        R.layout.item_movie
    ) {
        itemView.setOnClickListener { onClick(adapterPosition) }
        bind {
            with(itemView) {
                Glide.with(this)
                    .load(item.posterPath)
                    .into(ivPoster)

                tvTitle.text = item.title
            }

        }
    }