package com.example.cinema

import android.util.Log
import com.example.cinema.data.MoviesRepository
import com.example.cinema.data.MoviesRepositoryImpl
import com.example.cinema.data.remote.MoviesApi
import com.example.cinema.data.remote.MoviesRemoteSource
import com.example.cinema.movielistscreen.data.MovieListInteractor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

private const val BASE_URL =
    "https://gist.githubusercontent.com/LukyanovAnatoliy/eca5141dedc79751b3d0b339649e06a3/raw/38f9419762adf27c34a3f052733b296385b6aa8d/"
val appModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(
                object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        Log.d("OkHttp", message)
                    }
                }
            ).apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
    }
}

val remoteModule = module {

    single<MoviesApi> {
        get<Retrofit>().create(MoviesApi::class.java)
    }

    single<MoviesRemoteSource> {
        MoviesRemoteSource(get())
    }

    single<MoviesRepository> {
        MoviesRepositoryImpl(get())
    }

    single<MovieListInteractor> {
        MovieListInteractor(get())
    }
}

const val CINEMA_QUALIFIER = "CINEMA_QUALIFIER"
val navModule = module {

    single<Cicerone<Router>>(named(CINEMA_QUALIFIER)) {
        Cicerone.create()
    }

    single<NavigatorHolder>(named(CINEMA_QUALIFIER)) {
        get<Cicerone<Router>>(named(CINEMA_QUALIFIER)).navigatorHolder
    }

    single<Router>(named(CINEMA_QUALIFIER)) {
        get<Cicerone<Router>>(named(CINEMA_QUALIFIER)).router
    }
}