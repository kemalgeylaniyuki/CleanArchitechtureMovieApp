package com.example.udemymovie.cleanarchitecture.data.remote.dto

data class PopularDto(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)