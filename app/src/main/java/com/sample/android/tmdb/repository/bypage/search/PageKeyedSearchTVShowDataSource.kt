package com.sample.android.tmdb.repository.bypage.search

import android.content.Context
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import java.util.concurrent.Executor

class PageKeyedSearchTVShowDataSource(
        private val api: TVShowApi,
        private val query: String,
        retryExecutor: Executor,
        context: Context)
    : PageKeyedItemDataSource<TVShow>(retryExecutor, context) {

    override fun fetchItems(page: Int) = api.searchItems(page, query)
}