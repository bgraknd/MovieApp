package com.bugra.movieapp

import io.reactivex.functions.BiFunction

class MoviePageCombiner :
    BiFunction<Resource<PopularMovies>, Resource<NowPlayingMovies>, MoviesFragmentViewState> {

    override fun apply(
        popularMovies: Resource<PopularMovies>,
        nowPlayingMovies: Resource<NowPlayingMovies>
    ): MoviesFragmentViewState {
        return MoviesFragmentViewState(popularMovies, nowPlayingMovies)
    }
}