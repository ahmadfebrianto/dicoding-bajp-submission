package com.ahmadfebrianto.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity
import com.ahmadfebrianto.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private lateinit var movies: List<MovieEntity>
    private lateinit var tvShows: List<MovieEntity>

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        movies = DummyData.getMovies()
        tvShows = DummyData.getTvShows()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun showMainActivity() {

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                movies.size
            )
        )

        onView(withText("TV SHOWS")).perform(click())

        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                tvShows.size
            )
        )
    }

    @Test
    fun showDetailActivity() {

        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        checkDetailActivity(movies[0])

        onView(withContentDescription("Navigate up")).perform(click())

        onView(withText("TV SHOWS")).perform(click())

        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

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