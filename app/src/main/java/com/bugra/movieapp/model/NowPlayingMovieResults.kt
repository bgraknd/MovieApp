package com.bugra.movieapp.model

data class NowPlayingMovieResults(
    var popularity: Float?,
    var vote_count: Int?,
    var video: Boolean?,
    var poster_path: String?,
    var id: Int?,
    var adult: Boolean?,
    var backdrop_path: String?,
    var original_language: String?,
    var original_title: String?,
    var genre_ids: List<Int>?,
    var title: String?,
    var vote_average: Float?,
    var overview: String?,
    var release_date: String?
)