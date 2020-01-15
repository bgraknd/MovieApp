package com.bugra.movieapp

import com.bugra.movieapp.model.MovieResults

class MoviesItemViewState(val movie: MovieResults) {

    fun getPopularMovieTitle(): String {
        return movie.title ?: ""
    }

    fun getPopularMoviePoster(): String {
        return movie.poster_path ?: ""
    }

    fun getPopularMovieBackdrop(): String {
        return movie.backdrop_path ?: ""
    }

    fun getPopularMovieOverview(): String {
        return movie.overview ?: ""
    }
}