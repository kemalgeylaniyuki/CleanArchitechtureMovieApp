package com.example.udemymovie.cleanarchitecture.data.repository

import com.example.udemymovie.cleanarchitecture.data.remote.MovieAPI
import com.example.udemymovie.cleanarchitecture.data.remote.dto.MovieDetailDto
import com.example.udemymovie.cleanarchitecture.data.remote.dto.MoviesDto
import com.example.udemymovie.cleanarchitecture.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api : MovieAPI) : MovieRepository {
    override suspend fun getMovies(query: String, page: String): MoviesDto {
        return api.getMovies(query = query, page = page)
    }

    override suspend fun getMovieDetail(id: Int): MovieDetailDto {
        return api.getMovieDetail(id = id)
    }

}