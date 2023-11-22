package com.example.udemymovie.cleanarchitecture.presentation.movie_detail

import com.example.udemymovie.cleanarchitecture.domain.model.MovieDetail

data class MovieDetailState(

    val isLoading : Boolean = false,
    val movie : MovieDetail? = null,
    val error : String = "",

    )