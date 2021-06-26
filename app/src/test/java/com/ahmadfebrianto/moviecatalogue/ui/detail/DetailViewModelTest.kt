package com.ahmadfebrianto.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ahmadfebrianto.moviecatalogue.data.DummyData
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.CatalogRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovies = DummyData.getMovies()
        val dummyMovie = dummyMovies[0]
        val dummyMovieId = dummyMovie.movieId
        viewModel.setSelectedItem(dummyMovieId, DetailActivity.MOVIE)

        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(catalogRepository.getMovieById(dummyMovieId)).thenReturn(movie)
        val movieEntity = viewModel.getItem().value as MovieEntity
        verify(catalogRepository).getMovieById(dummyMovieId)

        assertNotNull(movieEntity)
        assertEquals(movieEntity.movieId, dummyMovie.movieId)
        assertEquals(movieEntity.poster_path, dummyMovie.poster_path)
        assertEquals(movieEntity.title, dummyMovie.title)
        assertEquals(movieEntity.rating, dummyMovie.rating)
        assertEquals(movieEntity.release_year, dummyMovie.release_year)
        assertEquals(movieEntity.description, dummyMovie.description)

        viewModel.getItem().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvShow() {
        val dummyTvShows = DummyData.getTvShows()
        val dummyTvShow = dummyTvShows[0]
        val dummyTvShowId = dummyTvShow.movieId
        viewModel.setSelectedItem(dummyTvShowId, DetailActivity.TV_SHOW)

        val tvShow = MutableLiveData<MovieEntity>()
        tvShow.value = dummyTvShow

        `when`(catalogRepository.getTvShowById(dummyTvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getItem().value as MovieEntity
        verify(catalogRepository).getTvShowById(dummyTvShowId)

        assertNotNull(tvShowEntity)
        assertEquals(tvShowEntity.poster_path, dummyTvShow.poster_path)
        assertEquals(tvShowEntity.movieId, dummyTvShow.movieId)
        assertEquals(tvShowEntity.title, dummyTvShow.title)
        assertEquals(tvShowEntity.rating, dummyTvShow.rating)
        assertEquals(tvShowEntity.release_year, dummyTvShow.release_year)
        assertEquals(tvShowEntity.description, dummyTvShow.description)

        viewModel.getItem().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}