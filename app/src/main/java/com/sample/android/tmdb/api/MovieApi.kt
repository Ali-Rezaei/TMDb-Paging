package com.sample.android.tmdb.api

import com.google.gson.annotations.SerializedName
import com.sample.android.tmdb.vo.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    fun popularMovies(@Query("page") page: Int): Call<MovieWrapper>

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    fun highestRatedMovies(@Query("page") page: Int): Call<MovieWrapper>

    @GET("3/movie/upcoming?language=en")
    fun upcomingMovies(@Query("page") page: Int): Call<MovieWrapper>

    @GET("3/tv/popular?language=en")
    fun popularTVShows(@Query("page") page: Int): Call<TVShowWrapper>

    @GET("3/tv/top_rated?language=en")
    fun topRatedTVShows(@Query("page") page: Int): Call<TVShowWrapper>

    @GET("3/discover/tv?language=en&sort_by=first_air_date.desc")
    fun latestTvShows(@Query("page") page: Int): Call<TVShowWrapper>

    @GET("3/search/movie?language=en")
    fun searchMovies(@Query("page") page: Int, @Query("query") query: String): Call<MovieWrapper>

    @GET("3/movie/{movieId}/videos")
    fun trailers(@Path("movieId") movieId: Int): Observable<VideoWrapper>

    @GET("3/movie/{movieId}/credits")
    fun cast(@Path("movieId") movieId: Int): Observable<CastWrapper>

    @GET("3/person/{personId}")
    fun person(@Path("personId") personId: Int): Observable<Person>

    class MovieWrapper(
            @SerializedName("results")
            val movies: List<Movie>
    )

    class VideoWrapper(
            @SerializedName("results")
            val videos: List<Video>
    )

    class CastWrapper(
            @SerializedName("cast")
            val cast: List<Cast>
    )

    class TVShowWrapper(
            @SerializedName("results")
            val tvShows: List<TVShow>
    )
}