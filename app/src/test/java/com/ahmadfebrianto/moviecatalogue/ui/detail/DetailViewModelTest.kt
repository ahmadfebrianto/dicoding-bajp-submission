package com.ahmadfebrianto.moviecatalogue.ui.detail

import androidx.test.core.app.ApplicationProvider
import com.ahmadfebrianto.moviecatalogue.utils.CatalogHelper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@Config(sdk = [Config.OLDEST_SDK])
@RunWith(RobolectricTestRunner::class)
class DetailViewModelTest {

    lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        CatalogHelper.application = ApplicationProvider.getApplicationContext()
        viewModel = DetailViewModel()
    }

    @Test
    fun setSelectedItem() {
        val dummyMovie = CatalogHelper.getMovies()[0]
        val dummyMovieId = dummyMovie.movieId
        viewModel.setSelectedItem(dummyMovieId)

        val movie = viewModel.getMovie()

        /*Tes jika variabel movie tidak kosong*/
        assertNotNull(movie)

        /*Tes jika data movieId sama*/
        assertEquals(movie.movieId, dummyMovie.movieId)
    }

    @Test
    fun getMovie() {
        val dummyMovies = CatalogHelper.getMovies()
        val dummyMovie = dummyMovies[0]
        val dummyMovieId = dummyMovie.movieId
        viewModel.setSelectedItem(dummyMovieId)
        val movie = viewModel.getMovie()

        /*Tes jika variabel movie tidak kosong*/
        assertNotNull(movie)

        /*Cek jika nilai attribut movie sama*/
        assertEquals(movie.movieId, dummyMovie.movieId)
        assertEquals(movie.poster_path, dummyMovie.poster_path)
        assertEquals(movie.title, dummyMovie.title)
        assertEquals(movie.rating, dummyMovie.rating)
        assertEquals(movie.release_year, dummyMovie.release_year)
        assertEquals(movie.description, dummyMovie.description)
    }

    @Test
    fun getTvShow() {
        val dummyTvShows = CatalogHelper.getTvShows()
        val dummyTvShow = dummyTvShows[0]
        val dummyTvShowId = dummyTvShow.movieId
        viewModel.setSelectedItem(dummyTvShowId)
        val tvShow = viewModel.getTvShow()

        /*Tes jika variabel tvShow tidak kosong*/
        assertNotNull(tvShow)

        /*Cek jika nilai atribut tv show sama*/
        assertEquals(tvShow.poster_path, dummyTvShow.poster_path)
        assertEquals(tvShow.movieId, dummyTvShow.movieId)
        assertEquals(tvShow.title, dummyTvShow.title)
        assertEquals(tvShow.rating, dummyTvShow.rating)
        assertEquals(tvShow.release_year, dummyTvShow.release_year)
        assertEquals(tvShow.description, dummyTvShow.description)
    }
}