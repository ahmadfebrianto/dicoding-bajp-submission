package com.ahmadfebrianto.moviecatalogue.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahmadfebrianto.moviecatalogue.data.source.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.di.Injection
import com.ahmadfebrianto.moviecatalogue.ui.detail.DetailViewModel
import com.ahmadfebrianto.moviecatalogue.ui.movies.MovieViewModel
import com.ahmadfebrianto.moviecatalogue.ui.tvshows.TvShowViewModel

class ViewModelFactory private constructor(private val catalogRepository: CatalogRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideContextToRepository(context)).apply {
                    instance = this
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(catalogRepository) as T
            }

            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(catalogRepository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(catalogRepository) as T
            }

            else -> {
                throw  Throwable("[!] Unknown ViewModelClass: ${modelClass.name}")
            }
        }
    }
}