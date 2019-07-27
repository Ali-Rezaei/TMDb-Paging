package com.sample.android.tmdb.repository.bypage.tvshow

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.vo.TVShow
import java.util.concurrent.Executor

class TVShowsDataSourceFactory(
        private val dataSource: MoviesRemoteDataSource,
        private val sortType: SortType?,
        private val query: String,
        private val retryExecutor: Executor)
    : ItemDataSourceFactory<TVShow, ItemApi.TVShowWrapper>() {

    override fun getDataSource(): PageKeyedItemDataSource<TVShow, ItemApi.TVShowWrapper> =
            PageKeyedTVShowsDataSource(dataSource = dataSource,
                    sortType = sortType,
                    query = query,
                    retryExecutor = retryExecutor)
}