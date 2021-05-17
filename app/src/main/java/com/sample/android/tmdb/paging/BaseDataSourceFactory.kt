package com.sample.android.tmdb.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sample.android.tmdb.domain.TmdbItem

abstract class BaseDataSourceFactory<T : TmdbItem> : DataSource.Factory<Int, T>() {

    private val _sourceLiveData = MutableLiveData<BasePageKeyedDataSource<T>>()
    val sourceLiveDataBase: LiveData<BasePageKeyedDataSource<T>>
        get() = _sourceLiveData

    protected abstract val dataSource: BasePageKeyedDataSource<T>

    override fun create(): DataSource<Int, T> {
        _sourceLiveData.postValue(dataSource)
        return dataSource

    }
}