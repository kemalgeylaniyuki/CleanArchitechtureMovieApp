package com.example.udemymovie.cleanarchitecture.domain.repository

import com.example.udemymovie.cleanarchitecture.data.remote.dto.PopularDto

interface MovieRepository {

    suspend fun getMovies(search : String) : PopularDto
    suspend fun getPopularMovies(page : String) : PopularDto

}