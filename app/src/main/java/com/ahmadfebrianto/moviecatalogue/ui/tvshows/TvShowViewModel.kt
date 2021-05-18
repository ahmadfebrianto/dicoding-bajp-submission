package com.ahmadfebrianto.moviecatalogue.ui.tvshows

import androidx.lifecycle.ViewModel
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity
import com.ahmadfebrianto.moviecatalogue.utils.CatalogHelper

class TvShowViewModel: ViewModel() {
    fun getTvShows(): List<MovieEntity> {
        return CatalogHelper.getTvShows()
    }
}