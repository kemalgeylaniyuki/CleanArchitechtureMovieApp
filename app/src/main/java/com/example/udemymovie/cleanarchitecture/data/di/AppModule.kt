package com.example.udemymovie.cleanarchitecture.data.di

import com.example.udemymovie.cleanarchitecture.data.remote.MovieAPI
import com.example.udemymovie.cleanarchitecture.data.repository.MovieRepositoryImpl
import com.example.udemymovie.cleanarchitecture.domain.repository.MovieRepository
import com.example.udemymovie.cleanarchitecture.util.Constants.BASE_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi() : MovieAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api : MovieAPI) : MovieRepository{
        return MovieRepositoryImpl(api = api)
    }

}