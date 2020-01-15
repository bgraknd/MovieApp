package com.bugra.movieapp

import com.bugra.movieapp.model.MovieResults
import io.reactivex.Single
import retrofit2.http.GET

interface MovieApiService {
    @GET("popularmovies.json")
    fun getPopularMovies(
        //   @Query("api_key") apiKey: String
    ): Single<List<MovieResults>>

    @GET("popularmovies.json")
    fun getNowPlayingMovies(
        //   @Query("api_key") apiKey: String
    ): Single<List<MovieResults>>
    //TODO: nowPlaying
}