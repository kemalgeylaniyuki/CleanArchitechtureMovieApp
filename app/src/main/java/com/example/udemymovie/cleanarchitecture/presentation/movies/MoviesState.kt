package com.example.udemymovie.cleanarchitecture.presentation.movies

import com.example.udemymovie.cleanarchitecture.domain.model.Movie

data class MoviesState(

    val isLoading : Boolean = false,
    val movies : List<Movie> = emptyList(),
    val error : String = "",
    val query : String = "batman",
    val page : String = "1"

)
