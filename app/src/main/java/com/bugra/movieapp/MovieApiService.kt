package com.bugra.movieapp

import com.bugra.movieapp.model.PopularMovies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Single<PopularMovies>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String
    ): Single<PopularMovies>
    //TODO: nowPlaying
}