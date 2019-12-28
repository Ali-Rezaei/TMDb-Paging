package com.sample.android.tmdb.repository.bypage.movie

import com.sample.android.tmdb.util.SortType
import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.domain.Movie
import java.util.concurrent.Executor

class MoviesDataSourceFactory(
        private val dataSource: RemoteDataSource,
        private val sortType: SortType,
        private val retryExecutor: Executor)
    : ItemDataSourceFactory<Movie, ItemApi.MovieWrapper>() {

    override fun getDataSource(): PageKeyedItemDataSource<Movie, ItemApi.MovieWrapper> =
            PageKeyedMovieDataSource(dataSource = dataSource,
                    sortType = sortType,
                    retryExecutor = retryExecutor)
}
