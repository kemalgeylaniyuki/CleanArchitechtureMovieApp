package com.example.udemymovie.cleanarchitecture.presentation.movies

sealed class MoviesEvent {

    data class Search(val query : String, val page : String) : MoviesEvent()

}