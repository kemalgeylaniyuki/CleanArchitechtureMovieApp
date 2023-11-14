package com.example.udemymovie.cleanarchitecture.domain.model

data class MovieDetail(

    val genre_ids: List<Int>,
    val id: Int,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,

)

