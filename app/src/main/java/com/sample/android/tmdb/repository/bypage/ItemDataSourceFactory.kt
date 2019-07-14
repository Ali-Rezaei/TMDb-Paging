package com.sample.android.tmdb.repository.bypage

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource

abstract class ItemDataSourceFactory<T, E> : DataSource.Factory<Int, T>() {

    val sourceLiveData = MutableLiveData<PageKeyedItemDataSource<T, E>>()

    protected abstract fun getDataSource(): PageKeyedItemDataSource<T, E>

    override fun create(): DataSource<Int, T> {
        val source = getDataSource()
        sourceLiveData.postValue(source)
        return source
    }
}