package com.sample.android.tmdb.ui.item.tvshow

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.ui.TmdbAdapter
import com.sample.android.tmdb.ui.TmdbClickCallback
import com.sample.android.tmdb.domain.TVShow

class TVShowAdapter(
        private val tmdbClickCallback: TmdbClickCallback<TVShow>,
        retryCallback: () -> Unit)
    : TmdbAdapter<TVShow>(retryCallback) {

    override val layoutID = R.layout.tv_show_item

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            with((holder as TVShowViewHolder).binding) {
                tvShow = it
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TVShowViewHolder.create(parent, tmdbClickCallback)
    }
}