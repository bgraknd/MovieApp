package com.bugra.movieapp

import com.bugra.movieapp.model.MovieResults
import io.reactivex.functions.BiFunction

class MoviePageCombiner :
    BiFunction<Resource<List<MovieResults>>, Resource<List<MovieResults>>/*TODO:nowPlaying*/, MoviesFragmentViewState> {

    override fun apply(
        popularMovies: Resource<List<MovieResults>>,
        nowPlayingMovies: Resource<List<MovieResults>>
        //TODO: nowPlaying
    ): MoviesFragmentViewState {
        return MoviesFragmentViewState(popularMovies, nowPlayingMovies)
    }
}