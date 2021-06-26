package com.ahmadfebrianto.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity

interface CatalogDataSource {

    fun getMovies(): LiveData<List<MovieEntity>>

    fun getTvShows(): LiveData<List<MovieEntity>>

    fun getMovieById(movieId: String): LiveData<MovieEntity>

    fun getTvShowById(tvShowId: String): LiveData<MovieEntity>
}