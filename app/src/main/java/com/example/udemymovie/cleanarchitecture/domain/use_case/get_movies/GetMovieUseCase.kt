package com.example.udemymovie.cleanarchitecture.domain.use_case.get_movies

import com.example.udemymovie.cleanarchitecture.data.remote.dto.toMovieList
import com.example.udemymovie.cleanarchitecture.domain.model.Movie
import com.example.udemymovie.cleanarchitecture.domain.repository.MovieRepository
import com.example.udemymovie.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    fun executeGetMovies(query : String, page : String) : Flow<Resource<List<Movie>>> = flow {
        try {

            emit(Resource.Loading())
            val movieList = repository.getMovies(query, page)
            if (movieList.total_results != 0){
                emit(Resource.Success(movieList.toMovieList()))
            }
            else{
                emit(Resource.Error(message = "No Movie Found!"))
            }

        } catch (e : IOError){
            emit(Resource.Error(message = "No Internet Connection!"))
        } catch (e : HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }
    }

}