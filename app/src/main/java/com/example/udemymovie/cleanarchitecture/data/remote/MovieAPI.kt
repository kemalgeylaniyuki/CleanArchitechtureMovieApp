package com.example.udemymovie.cleanarchitecture.data.remote

import com.example.udemymovie.cleanarchitecture.data.remote.dto.Genres
import com.example.udemymovie.cleanarchitecture.data.remote.dto.MovieDetailDto
import com.example.udemymovie.cleanarchitecture.data.remote.dto.MoviesDto
import com.example.udemymovie.cleanarchitecture.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("3/genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") api_key: String = API_KEY
    ): Genres

    @GET("3/search/movie")
    suspend fun getMovies(
        @Query("query") query: String,
        @Query("page") page: String,
        @Query("api_key") api_key: String = API_KEY
    ): MoviesDto

    @GET("3/movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = API_KEY
    ): MovieDetailDto

}

//https://api.themoviedb.org/3/movie/268?api_key=5047ef5641b3b674225c558da29d10bd