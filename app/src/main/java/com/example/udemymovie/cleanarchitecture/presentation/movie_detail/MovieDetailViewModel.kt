package com.example.udemymovie.cleanarchitecture.presentation.movie_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.udemymovie.cleanarchitecture.domain.use_case.get_movie_detail.GetMovieDetailUSeCase
import com.example.udemymovie.cleanarchitecture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase : GetMovieDetailUSeCase,
    private val savedStateHandle : SavedStateHandle ) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailState())
    val state : StateFlow<MovieDetailState> = _state

    private var job : Job? = null

    /*
    init {
        savedStateHandle.get<Int>("id")?.let {
            getMovieDetail(it)
        }
    }

     */

     fun getMovieDetail(id : Int){

        job =  getMovieDetailUseCase.executeGetMovieDetails(id = id).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = MovieDetailState(movie = it.data)
                }

                is Resource.Error -> {
                    _state.value = MovieDetailState(error = it.message ?: "Error!")

                }

                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }



}