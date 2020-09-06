package com.sample.android.tmdb.ui.detail.tvshow

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.FragmentDetailTvShowBinding
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.ui.detail.DetailFragment
import org.jetbrains.annotations.Nullable
import javax.inject.Inject

class TVShowDetailFragment @Inject
constructor() // Required empty public constructor
    : DetailFragment<TVShow>() {

    @Inject
    @Nullable
    lateinit var tvShowItem: TVShow

    @Inject
    lateinit var api: TVShowApi

    override val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TVShowDetailViewModel(api, tvShowItem) as T
            }
        })[TVShowDetailViewModel::class.java]
    }

    override val layoutId = R.layout.fragment_detail_tv_show

    override val tmdbItem: TVShow by lazy { tvShowItem }

    override fun getViewBinding(root: View): FragmentDetailTvShowBinding =
            FragmentDetailTvShowBinding.bind(root).apply {
                tvShow = tvShowItem
            }
}