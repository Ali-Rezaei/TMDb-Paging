package com.sample.android.tmdb.ui.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.ui.detail.DetailActivity.Companion.EXTRA_MOVIE
import com.sample.android.tmdb.ui.movie.MovieAdapter
import com.sample.android.tmdb.ui.movie.MovieViewModel
import com.sample.android.tmdb.vo.Movie
import javax.inject.Inject

@ActivityScoped
class SearchMovieFragment @Inject
constructor() // Required empty public constructor
    : SearchBaseFragment<Movie>() {

    override fun initViewModel() {
        model = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(dataSource = dataSource,
                        sortType = getSortType()) as T
            }
        })[MovieViewModel::class.java]
    }

    override fun getAdapter(retryCallback: () -> Unit): ItemAdapter<Movie> = MovieAdapter(this, retryCallback)

    override fun putItemParcelable(bundle: Bundle, t: Movie) {
        bundle.putParcelable(EXTRA_MOVIE, t)
    }
}