package com.ahmadfebrianto.moviecatalogue.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ahmadfebrianto.moviecatalogue.data.DummyData
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.CatalogRepository
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogRepository)
    }


    @Test
    fun getTvShows() {

        val dummyTvShows = DummyData.getTvShows()
        val tvShows = MutableLiveData<List<MovieEntity>>()
        tvShows.value = dummyTvShows

        `when`(catalogRepository.getTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShows().value
        verify(catalogRepository).getTvShows()
        assertNotNull(tvShowEntities)
        assertTrue(tvShowEntities?.size!! >= 10)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)

    }
}