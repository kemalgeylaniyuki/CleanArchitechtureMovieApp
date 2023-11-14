package com.example.udemymovie.cleanarchitecture.data.repository

import com.example.udemymovie.cleanarchitecture.data.remote.MovieAPI
import com.example.udemymovie.cleanarchitecture.data.remote.dto.PopularDto
import com.example.udemymovie.cleanarchitecture.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api : MovieAPI) : MovieRepository {
    override suspend fun getMovies(search: String): PopularDto {
        return api.getMovies(query = search)
    }

    override suspend fun getPopularMovies(page: String): PopularDto {
        return api.getPopularMovies(page = page)
    }
}