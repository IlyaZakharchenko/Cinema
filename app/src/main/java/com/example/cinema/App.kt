package com.example.cinema

import android.app.Application
import com.example.cinema.moviedetailsscreen.movieDetailsModule
import com.example.cinema.movielistscreen.movieListModule
import com.example.cinema.movieplayerscreen.moviePlayerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                remoteModule,
                navModule,
                movieDetailsModule,
                movieListModule,
                moviePlayerModule
            )
        }
    }
}