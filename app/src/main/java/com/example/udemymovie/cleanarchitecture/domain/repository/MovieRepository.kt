package com.example.udemymovie.cleanarchitecture.domain.repository

import com.example.udemymovie.cleanarchitecture.data.remote.dto.MovieDetailDto
import com.example.udemymovie.cleanarchitecture.data.remote.dto.MoviesDto

interface MovieRepository {

    suspend fun getMovies(query : String, page: String) : MoviesDto
    suspend fun getMovieDetail(id : Int) : MovieDetailDto

}