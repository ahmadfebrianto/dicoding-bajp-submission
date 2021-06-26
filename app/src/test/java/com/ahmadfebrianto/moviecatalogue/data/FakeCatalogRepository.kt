package com.ahmadfebrianto.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmadfebrianto.moviecatalogue.data.source.CatalogDataSource
import com.ahmadfebrianto.moviecatalogue.data.source.local.LocalDataSource

class FakeCatalogRepository(private val localDataSource: LocalDataSource) : CatalogDataSource {

    override fun getMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        localDataSource.getMovies(object : LocalDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(listMovies: List<MovieEntity>) {
                movieResults.postValue(listMovies)
            }

        })
        return movieResults

    }

    override fun getTvShows(): LiveData<List<MovieEntity>> {
        val tvShowsResult = MutableLiveData<List<MovieEntity>>()
        localDataSource.getTvShows(object : LocalDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(listTvShows: List<MovieEntity>) {
                tvShowsResult.postValue(listTvShows)
            }
        })
        return tvShowsResult
    }

    override fun getMovieById(movieId: String): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        localDataSource.getMovies(object : LocalDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(listMovies: List<MovieEntity>) {
                lateinit var movie: MovieEntity
                for (item in listMovies) {
                    if (item.movieId == movieId) {
                        movie = item
                        break
                    }
                }
                movieResult.postValue(movie)
            }

        })

        return movieResult
    }

    override fun getTvShowById(tvShowId: String): LiveData<MovieEntity> {
        val tvShowResult = MutableLiveData<MovieEntity>()
        localDataSource.getTvShows(object : LocalDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(listTvShows: List<MovieEntity>) {
                lateinit var tvShow: MovieEntity
                for (item in listTvShows) {
                    if (item.movieId == tvShowId) {
                        tvShow = item
                        break
                    }
                }
                tvShowResult.postValue(tvShow)
            }
        })

        return tvShowResult
    }

}