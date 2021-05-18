package com.ahmadfebrianto.moviecatalogue.ui.movies

import androidx.lifecycle.ViewModel
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity
import com.ahmadfebrianto.moviecatalogue.utils.CatalogHelper

class MovieViewModel : ViewModel() {
    fun getMovies(): List<MovieEntity> {
        return CatalogHelper.getMovies()
    }
}