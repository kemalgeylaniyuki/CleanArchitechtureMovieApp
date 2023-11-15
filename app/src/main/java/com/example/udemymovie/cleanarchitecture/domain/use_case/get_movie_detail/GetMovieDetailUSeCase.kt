package com.example.udemymovie.cleanarchitecture.domain.use_case.get_movie_detail
import com.example.udemymovie.cleanarchitecture.data.remote.dto.toMovieDetail
import com.example.udemymovie.cleanarchitecture.domain.model.MovieDetail
import com.example.udemymovie.cleanarchitecture.domain.repository.MovieRepository
import com.example.udemymovie.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailUSeCase @Inject constructor(private val repository: MovieRepository) {

    fun executeGetMovieDetails(id : Int) : Flow<Resource<MovieDetail>> = flow {

        try {

            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(id = id)
            emit(Resource.Success(movieDetail.toMovieDetail()))

        } catch (e : IOError){
            emit(Resource.Error(message = "No Internet Connection!"))
        } catch (e : HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }


    }

}