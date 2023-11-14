package com.example.udemymovie.cleanarchitecture.data.remote.dto

import com.example.udemymovie.cleanarchitecture.domain.model.Movie
import com.example.udemymovie.cleanarchitecture.domain.model.MovieDetail


data class PopularDto(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

fun PopularDto.toMovieList() : List<Movie>{
    return results.map { result -> Movie(result.poster_path, result.title, result.release_date, result.id) }
}
