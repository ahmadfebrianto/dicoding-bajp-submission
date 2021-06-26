package com.ahmadfebrianto.moviecatalogue.data.source.local

import android.os.Handler
import android.os.Looper
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity
import com.ahmadfebrianto.moviecatalogue.utils.CatalogHelper
import com.ahmadfebrianto.moviecatalogue.utils.EspressoIdlingResource

class LocalDataSource private constructor(private val catalogHelper: CatalogHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {

        private const val SERVICE_LATENCY_IN_MILLIS: Long = 1000

        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstanceHelper(helper: CatalogHelper): LocalDataSource {
            return instance ?: synchronized(this) {
                LocalDataSource(helper).apply {
                    instance = this
                }
            }
        }
    }

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllMoviesReceived(catalogHelper.getMovies())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllTvShowsReceived(catalogHelper.getTvShows())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(listMovies: List<MovieEntity>)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(listTvShows: List<MovieEntity>)
    }
}