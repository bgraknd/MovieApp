package com.bugra.movieapp

data class PopularMovies(
    var page: Int?,
    var total_results: Int?,
    var total_pages: Int?,
    var results: List<PopularMovieResults>?
)