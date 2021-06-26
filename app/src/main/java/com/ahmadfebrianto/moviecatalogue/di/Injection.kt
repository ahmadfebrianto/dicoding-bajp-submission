package com.ahmadfebrianto.moviecatalogue.di

import android.content.Context
import com.ahmadfebrianto.moviecatalogue.data.source.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.data.source.local.LocalDataSource
import com.ahmadfebrianto.moviecatalogue.utils.CatalogHelper


object Injection {
    fun provideContextToRepository(context: Context): CatalogRepository {

        val localDataSource = LocalDataSource.getInstanceHelper(CatalogHelper(context))

        return CatalogRepository.getInstance(localDataSource)
    }
}