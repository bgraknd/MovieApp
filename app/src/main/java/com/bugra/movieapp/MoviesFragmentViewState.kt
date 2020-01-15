package com.bugra.movieapp

import android.view.View
import com.bugra.movieapp.Status.LOADING
import com.bugra.movieapp.model.MovieResults

data class MoviesFragmentViewState(
    private val popularMovies: Resource<List<MovieResults>>,
    private val nowPlayingMovies: Resource<List<MovieResults>>
//TODO: nowPlaying
) {
    fun getPopularMovies() = popularMovies.data ?: arrayListOf()

    fun getNowPlayingMovies() = nowPlayingMovies.data ?: arrayListOf()

    fun getPopularMoviesLoadingVisibility(): Int {
        return when (popularMovies.status) {
            LOADING -> View.VISIBLE
            else -> View.GONE
        }
    }

    fun getNowPlayingMoviesLoadingVisibility(): Int {
        return when (nowPlayingMovies.status) {
            LOADING -> View.VISIBLE
            else -> View.GONE
        }
    }
}
