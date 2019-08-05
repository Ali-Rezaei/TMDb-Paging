package com.sample.android.tmdb.vo

import android.os.Parcelable

interface TmdbItem : Parcelable {

    val id : Int

    val overview: String

    val posterPath: String?

    val backdropPath: String?

    val voteAverage: Double
}