package com.sample.android.tmdb.ui.movie

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.ui.MainFragment
import com.sample.android.tmdb.ui.detail.DetailActivity.Companion.EXTRA_MOVIE
import com.sample.android.tmdb.domain.Movie

abstract class MovieFragment : MainFragment<Movie>() {

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(dataSource = dataSource,
                        sortType = getSortType()) as T
            }
        })[MovieViewModel::class.java]
    }

    override fun getAdapter(retryCallback: () -> Unit): ItemAdapter<Movie> = MovieAdapter(this, retryCallback)

    override fun getKeyParcelable() = EXTRA_MOVIE
}