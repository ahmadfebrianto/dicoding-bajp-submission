package com.ahmadfebrianto.moviecatalogue.ui.tvshows

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
class TvShowViewModelTest {

    lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        CatalogHelper.application = ApplicationProvider.getApplicationContext()
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShows = viewModel.getTvShows()

        /*Tes jika variabel tvShows tidak kosong*/
        assertNotNull(tvShows)

        /*Tes jika jumlah tvShows 10 atau lebih*/
        assertTrue(tvShows.size >= 10)
    }
}