package com.sample.android.tmdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.android.tmdb.domain.*
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.ui.detail.movie.MovieDetailViewModel
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: MovieApi

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun loadTrailersAndCredits() {
        val trailers = VideoWrapper(listOf(Video("id", "", "", "", "")))
        val creditWrapper = CreditWrapper(listOf(Cast("", "", null, 1)), listOf())
        `when`(api.movieTrailers(anyInt())).thenReturn(Observable.just(trailers))
        `when`(api.movieCredit(anyInt())).thenReturn(Observable.just(creditWrapper))

        val movie = Movie(1, "", null, null, null, "", 1.1)
        val viewModel = MovieDetailViewModel(api, movie)

        viewModel.liveData.value?.let {
            assertTrue(it.videos.size == 1)
            assertTrue(it.creditWrapper.cast.size == 1)
            assertTrue(it.creditWrapper.crew.isEmpty())
            assertTrue(it.videos[0].id == "id")
            assertTrue(it.creditWrapper.cast[0].id == 1)
        }
    }

    @Test
    fun errorLoadTrailers() {
        val creditWrapper = CreditWrapper(listOf(Cast("", "", null, 1)), listOf())
        `when`(api.movieTrailers(anyInt())).thenReturn(Observable.error(Exception()))
        `when`(api.movieCredit(anyInt())).thenReturn(Observable.just(creditWrapper))

        val movie = Movie(1, "", null, null, null, "", 1.1)
        val viewModel = MovieDetailViewModel(api, movie)

        with(viewModel.liveData.value) {
            assertThat(this, `is`(nullValue()))
        }
    }

    @Test
    fun errorLoadCredits() {
        val trailers = VideoWrapper(listOf(Video("id", "", "", "", "")))
        `when`(api.movieTrailers(anyInt())).thenReturn(Observable.just(trailers))
        `when`(api.movieCredit(anyInt())).thenReturn(Observable.error(Exception()))

        val movie = Movie(1, "", null, null, null, "", 1.1)
        val viewModel = MovieDetailViewModel(api, movie)

        with(viewModel.liveData.value) {
            assertThat(this, `is`(nullValue()))
        }
    }
}