package com.bugra.movieapp.model

data class NowPlayingMovies(
    var results: List<NowPlayingMovieResults>?,
    var page: Int?,
    var total_results: Int?,
    var dates: NowPlayingMoviesDates?,
    var total_pages: Int?
)