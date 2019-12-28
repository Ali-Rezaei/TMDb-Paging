package com.sample.android.tmdb.ui.item.movie

import com.sample.android.tmdb.util.SortType.UPCOMING
import javax.inject.Inject

class UpcomingMoviesFragment @Inject
constructor() : MovieFragment() {

    override val sortType = UPCOMING
}