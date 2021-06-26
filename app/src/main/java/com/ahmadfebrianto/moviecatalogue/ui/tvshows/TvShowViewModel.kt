package com.ahmadfebrianto.moviecatalogue.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.CatalogRepository

class TvShowViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getTvShows(): LiveData<List<MovieEntity>> {
        return catalogRepository.getTvShows()
    }
}