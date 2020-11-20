package com.example.cinema

import android.widget.ImageView
import com.bumptech.glide.Glide

inline fun <reified T> attempt(func: () -> T): Either<Throwable, T> = try {
    Either.Right(func.invoke())
} catch (e: Throwable) {
    Either.Left(e)
}

fun ImageView.setImage(url: String) {
    Glide.with(context)
        .load(url)
        .fitCenter()
        .into(this)
}