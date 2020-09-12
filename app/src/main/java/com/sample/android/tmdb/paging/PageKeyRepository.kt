package com.sample.android.tmdb.paging

import androidx.lifecycle.Transformations
import androidx.annotation.MainThread
import androidx.paging.LivePagedListBuilder
import com.sample.android.tmdb.domain.TmdbItem
import java.util.concurrent.Executor

abstract class PageKeyRepository<T : TmdbItem>: ItemRepository<T> {

    protected abstract fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<T>

    @MainThread
    override fun getItems(query: String, networkExecutor: Executor): Listing<T> {

        val sourceFactory = getSourceFactory(query, networkExecutor)

        val livePagedList = LivePagedListBuilder(sourceFactory, 20)
                // provide custom executor for network requests, otherwise it will default to
                // Arch Components' IO pool which is also used for disk access
                .setFetchExecutor(networkExecutor)
                .build()

        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }
        return Listing(
                pagedList = livePagedList,
                networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                    it.networkState
                },
                refresh = {
                    sourceFactory.sourceLiveData.value?.invalidate()
                },
                refreshState = refreshState,
                retry = {
                    sourceFactory.sourceLiveData.value?.retryAllFailed()
                }
        )
    }
}