package com.ahmadfebrianto.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.CatalogRepository

class DetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    private lateinit var itemId: String
    private lateinit var itemType: String

    fun setSelectedItem(itemId: String, itemType: String) {
        this.itemId = itemId
        this.itemType = itemType
    }

    fun getItem(): LiveData<MovieEntity> {
        return when (itemType) {
            DetailActivity.MOVIE -> catalogRepository.getMovieById(itemId)
            else -> catalogRepository.getTvShowById(itemId)
        }
    }
}