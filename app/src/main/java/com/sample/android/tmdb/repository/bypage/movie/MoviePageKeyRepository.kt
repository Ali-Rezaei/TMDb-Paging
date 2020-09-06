package com.sample.android.tmdb.repository.bypage.movie

import android.content.Context
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import com.sample.android.tmdb.util.SortType
import java.util.concurrent.Executor

class MoviePageKeyRepository(
        private val api: MovieApi,
        private val sortType: SortType,
        private val context: Context)
    : PageKeyRepository<Movie>() {

    override fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<Movie> =
            MoviesDataSourceFactory(api = api,
                    sortType = sortType,
                    retryExecutor = retryExecutor,
                    context = context)
}