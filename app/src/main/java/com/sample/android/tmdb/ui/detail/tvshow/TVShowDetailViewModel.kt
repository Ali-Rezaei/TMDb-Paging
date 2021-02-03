package com.sample.android.tmdb.ui.detail.tvshow

import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.domain.VideoWrapper
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.ui.detail.DetailViewModel
import io.reactivex.Observable

class TVShowDetailViewModel(
        api: TVShowApi,
        item: TmdbItem
) : DetailViewModel() {

    override val trailers: Observable<VideoWrapper> = api.tvTrailers(item.id)

    override val credits: Observable<CreditWrapper> = api.tvCredit(item.id)
}