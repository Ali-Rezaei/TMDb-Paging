package com.sample.android.tmdb.paging.tvshow

import android.content.Context
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.TmdbDataSourceFactory
import com.sample.android.tmdb.paging.PageKeyedItemDataSource
import com.sample.android.tmdb.ui.item.SortType
import java.util.concurrent.Executor

class TVShowsDataSourceFactory(
        api: TVShowApi,
        sortType: SortType,
        retryExecutor: Executor,
        context: Context
) : TmdbDataSourceFactory<TVShow>() {

    override val dataSource: PageKeyedItemDataSource<TVShow> by lazy {
        PageKeyedTVShowsDataSource(api = api,
                sortType = sortType,
                retryExecutor = retryExecutor,
                context = context)
    }
}