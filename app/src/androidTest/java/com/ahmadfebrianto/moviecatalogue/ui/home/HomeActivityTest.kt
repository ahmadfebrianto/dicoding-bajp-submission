package com.ahmadfebrianto.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity
import com.ahmadfebrianto.moviecatalogue.utils.CatalogHelper
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private lateinit var movies: ArrayList<MovieEntity>
    private lateinit var tvShows: ArrayList<MovieEntity>

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        CatalogHelper.application = ApplicationProvider.getApplicationContext()
        movies = CatalogHelper.getMovies()
        tvShows = CatalogHelper.getTvShows()
    }

    /*Memastikan Semua Komponen Main Activity Tampil*/
    @Test
    fun showMainActivity() {

        /*Memastikan fragment MOVIES tampil*/
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))

        /*Scroll fragment MOVIES*/
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                movies.size
            )
        )

        /*Klik fragment TV SHOWS*/
        onView(withText("TV SHOWS")).perform(click())

        /*Memastikan fragment TV SHOWS tampil*/
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))

        /*Scroll fragment TV SHOWS*/
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                tvShows.size
            )
        )
    }

    /*Memastikan Detail Activity Dapat Diakses*/
    @Test
    fun showDetailActivity() {

        /*Klik item paling atas dari MOVIES*/
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        /*Cek Tampilan Data Movie*/
        checkDetailActivity(movies[0])

        /*Klik Toolbar Back Button*/
        onView(withContentDescription("Navigate up")).perform(click())

        /*Klik Fragment TV SHOWS*/
        onView(withText("TV SHOWS")).perform(click())

        /*Klik item paling atas dari TV SHOWS*/
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        /*Cek Tampilan Data Tv Show*/
        checkDetailActivity(tvShows[0])
    }

    private fun checkDetailActivity(item: MovieEntity) {
        onView(withId(R.id.iv_movie_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_title)).check(matches(withText(item.title)))
        onView(withId(R.id.tv_movie_rating)).check(matches(withText(item.rating)))
        onView(withId(R.id.tv_movie_release_year)).check(matches(withText(item.release_year)))
        onView(withId(R.id.tv_movie_description)).check(matches(withText(item.description)))
    }
}