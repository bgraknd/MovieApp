package com.bugra.movieapp

data class MoviesFragmentViewState(
    val popularMovies: Resource<PopularMovies>,
    val nowPlayingMovies: Resource<NowPlayingMovies>
)