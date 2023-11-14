package com.example.udemymovie.cleanarchitecture.data.remote

import com.example.udemymovie.cleanarchitecture.data.remote.dto.GenreDto
import com.example.udemymovie.cleanarchitecture.data.remote.dto.PopularDto
import com.example.udemymovie.cleanarchitecture.data.remote.dto.RecentDto
import com.example.udemymovie.cleanarchitecture.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page : String,
        @Query("api_key") api_key : String = API_KEY
    ) : PopularDto

    @GET("3/movie/now_playing")
    suspend fun getRecentMovie(
        @Query("page") page : String,
        @Query("api_key") api_key : String = API_KEY
    ) : RecentDto

    @GET("3/genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") api_key : String = API_KEY
    ) : GenreDto

    @GET("3/search/movie")
    suspend fun getMovies(
        @Query("query") query : String,
        @Query("api_key") api_key : String = API_KEY
    ) : PopularDto


}