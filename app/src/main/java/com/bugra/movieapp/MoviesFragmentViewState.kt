package com.bugra.movieapp

import com.bugra.movieapp.model.PopularMovies

data class MoviesFragmentViewState(
    val popularMovies: Resource<PopularMovies>,
    val nowPlayingMovies: Resource<PopularMovies>
    //TODO: nowPlaying
)