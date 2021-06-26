package com.ahmadfebrianto.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmadfebrianto.moviecatalogue.data.source.local.LocalDataSource
import com.ahmadfebrianto.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock

class CatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val local = mock(LocalDataSource::class.java)
    private val catalogRepository = FakeCatalogRepository(local)

    private val dummyMovieEntities = DummyData.getMovies()
    private val dummyMovieEntity = dummyMovieEntities[0]
    private val dummyMovieId = dummyMovieEntity.movieId

    private val dummyTvShowsEntities = DummyData.getTvShows()
    private val dummyTvShowEntity = dummyTvShowsEntities[0]
    private val dummyTvShowId = dummyTvShowEntity.movieId


    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LocalDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(dummyMovieEntities)
            null
        }.`when`(local).getMovies(any())

        val movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovies())
        verify(local).getMovies(any())

        assertNotNull(movieEntities)
        assertEquals(dummyMovieEntities.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LocalDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(dummyTvShowsEntities)
            null
        }.`when`(local).getTvShows(any())

        val tvShowsEntities = LiveDataTestUtil.getValue(catalogRepository.getTvShows())
        verify(local).getTvShows(any())

        assertNotNull(tvShowsEntities)
        assertEquals(dummyTvShowsEntities.size.toLong(), tvShowsEntities.size.toLong())
    }

    @Test
    fun getMovieById() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LocalDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(dummyMovieEntities)
            null
        }.`when`(local).getMovies(any())

        val movieEntity = LiveDataTestUtil.getValue(catalogRepository.getMovieById(dummyMovieId))
        verify(local).getMovies(any())

        assertNotNull(movieEntity)
        assertNotNull(movieEntity.title)
        assertEquals(dummyMovieEntities[0].title, movieEntity.title)
    }

    @Test
    fun getTvShowById() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LocalDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(dummyTvShowsEntities)
            null
        }.`when`(local).getTvShows(any())

        val tvShowsEntity =
            LiveDataTestUtil.getValue(catalogRepository.getTvShowById(dummyTvShowId))
        verify(local).getTvShows(any())

        assertNotNull(tvShowsEntity)
        assertNotNull(tvShowsEntity.title)
        assertEquals(dummyTvShowsEntities[0].title, tvShowsEntity.title)
    }
}