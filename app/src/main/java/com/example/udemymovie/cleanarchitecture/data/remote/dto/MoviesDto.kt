package com.example.udemymovie.cleanarchitecture.data.remote.dto

import com.example.udemymovie.cleanarchitecture.domain.model.Movie

data class MoviesDto(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

fun MoviesDto.toMovieList() : List<Movie> {
    return results.map { result -> Movie(result.poster_path, result.title, result.release_date, result.id, result.vote_average) }
}