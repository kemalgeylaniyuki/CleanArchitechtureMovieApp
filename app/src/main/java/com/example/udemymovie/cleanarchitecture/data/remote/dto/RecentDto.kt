package com.example.udemymovie.cleanarchitecture.data.remote.dto

data class RecentDto(
    val dates: Dates,
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)