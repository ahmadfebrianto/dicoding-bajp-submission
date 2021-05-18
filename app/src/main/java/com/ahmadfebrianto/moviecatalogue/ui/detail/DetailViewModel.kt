package com.ahmadfebrianto.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity
import com.ahmadfebrianto.moviecatalogue.utils.CatalogHelper

class DetailViewModel: ViewModel() {

    private lateinit var itemId: String

    fun setSelectedItem(itemId: String) {
        this.itemId = itemId
    }

    fun getMovie(): MovieEntity {
        lateinit var movie: MovieEntity
        val movies = CatalogHelper.getMovies()

        for (item in movies) {
            if (item.movieId == itemId) {
                movie = item
            }
        }
        return movie
    }

    fun getTvShow(): MovieEntity {
        lateinit var tvShow: MovieEntity
        val tvShows = CatalogHelper.getTvShows()

        for (item in tvShows) {
            if (item.movieId == itemId) {
                tvShow = item
            }
        }
        return tvShow
    }
}