package com.ahmadfebrianto.moviecatalogue.ui.movies

import androidx.test.core.app.ApplicationProvider
import com.ahmadfebrianto.moviecatalogue.utils.CatalogHelper
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@Config(sdk = [Config.OLDEST_SDK])
@RunWith(RobolectricTestRunner::class)
class MovieViewModelTest {

    lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        CatalogHelper.application = ApplicationProvider.getApplicationContext()
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val movies = viewModel.getMovies()

        /*Tes jika variable movies tidak kosong*/
        assertNotNull(movies)

        /*Tes jika jumlah movies 10 atau lebih*/
        assertTrue(movies.size >= 10)
    }
}