package com.example.udemymovie.cleanarchitecture.domain.model

import com.example.udemymovie.cleanarchitecture.data.remote.dto.Genre

data class MovieDetail(

    val genres: List<Genre>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val release_date: String,
    val status: String,
    val tagline: String,
    val vote_average: Double,
    val poster_path: String,
    val id: Int

)

