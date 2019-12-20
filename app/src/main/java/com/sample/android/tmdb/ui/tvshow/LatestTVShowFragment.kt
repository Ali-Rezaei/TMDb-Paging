package com.sample.android.tmdb.ui.tvshow

import com.sample.android.tmdb.util.SortType.UPCOMING
import javax.inject.Inject

class LatestTVShowFragment @Inject
constructor() : TVShowFragment() {

    override val sortType = UPCOMING
}