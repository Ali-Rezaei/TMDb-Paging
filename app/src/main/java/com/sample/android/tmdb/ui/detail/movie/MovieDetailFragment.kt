package com.sample.android.tmdb.ui.detail.movie

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.view.View
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.FragmentDetailMovieBinding
import com.sample.android.tmdb.ui.detail.DetailFragment
import com.sample.android.tmdb.domain.Movie
import org.jetbrains.annotations.Nullable
import javax.inject.Inject

class MovieDetailFragment @Inject
constructor() // Required empty public constructor
    : DetailFragment<Movie>() {

    @Inject
    @Nullable
    lateinit var movieItem: Movie

    override fun getViewModel() =
            ViewModelProviders.of(requireActivity(), object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return MovieDetailViewModel(dataSource, movieItem) as T
                }
            })[MovieDetailViewModel::class.java]

    override val layoutId = R.layout.fragment_detail_movie

    override fun getViewBinding(root: View): FragmentDetailMovieBinding =
            FragmentDetailMovieBinding.bind(root).apply {
                movie = movieItem
            }

    override fun getTmdbItem(): Movie = movieItem
}