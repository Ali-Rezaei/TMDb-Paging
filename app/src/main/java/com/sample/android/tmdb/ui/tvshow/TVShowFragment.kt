package com.sample.android.tmdb.ui.tvshow

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.ui.MainFragment
import com.sample.android.tmdb.ui.detail.DetailActivity.Companion.EXTRA_TV_SHOW
import com.sample.android.tmdb.vo.TVShow

abstract class TVShowFragment : MainFragment<TVShow>() {

    override fun initViewModel() {
        model = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TVShowsViewModel(dataSource = dataSource,
                        sortType = getSortType()) as T
            }
        })[TVShowsViewModel::class.java]
        model.showQuery("")
    }

    override fun getAdapter(retryCallback: () -> Unit): ItemAdapter<TVShow> = TVShowAdapter(this, retryCallback)

    override fun getKeyParcelable() = EXTRA_TV_SHOW
}