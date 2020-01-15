package com.bugra.movieapp

import com.bugra.movieapp.model.PopularMovies
import io.reactivex.functions.BiFunction

class MoviePageCombiner :
    BiFunction<Resource<PopularMovies>, Resource<PopularMovies>/*TODO:nowPlaying*/, MoviesFragmentViewState> {

    override fun apply(
        popularMovies: Resource<PopularMovies>,
        nowPlayingMovies: Resource<PopularMovies>
        //TODO: nowPlaying
    ): MoviesFragmentViewState {
        return MoviesFragmentViewState(popularMovies, nowPlayingMovies)
    }
}