package com.sample.android.tmdb

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.sample.android.tmdb.TestUtils.nestedScrollTo
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.detail.credit.CreditAdapter
import com.sample.android.tmdb.ui.item.movie.MovieViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestMainActivity : TestBase() {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun shouldBeAbleToLaunchMainScreen() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.action_search)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToLoadMovies() {
        onView(withId(R.id.list)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToScrollViewMovieDetails() {
        onView(withId(R.id.list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
        onView(withText(R.string.summary)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayTrailerLabel() {
        onView(withId(R.id.list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
        onView(withText(R.string.trailers)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayCastLabel() {
        onView(withId(R.id.list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
        onView(withText(R.string.cast)).perform(nestedScrollTo()).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayCast() {
        onView(withId(R.id.list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
        onView(withId(R.id.credit_list)).perform(nestedScrollTo()).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayPersonDetail() {
        onView(withId(R.id.list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
        onView(withId(R.id.credit_list)).perform(nestedScrollTo()).perform(RecyclerViewActions
                .actionOnItemAtPosition<CreditAdapter.CastViewHolder>(2, click()))
        onView(withText(R.string.biography)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToChangeTabAndViewDetails() {
        onView(withId(R.id.action_upcoming)).perform(click())
        onView(withId(R.id.list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
        onView(withText(R.string.summary)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToSearchItem() {
        onView(withId(R.id.action_search)).perform(click())
        onView(isAssignableFrom(EditText::class.java))
                .perform(typeText("Harry Potter"),
                        pressImeActionButton())
        onView(withId(R.id.list)).check(matches(isDisplayed()))
    }
}
