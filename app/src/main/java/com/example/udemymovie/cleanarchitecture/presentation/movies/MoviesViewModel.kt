package com.example.udemymovie.cleanarchitecture.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.udemymovie.cleanarchitecture.domain.use_case.get_movies.GetMovieUseCase
import com.example.udemymovie.cleanarchitecture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase) : ViewModel() {

    private val _state = MutableStateFlow(MoviesState())
    val state : StateFlow<MoviesState> = _state

    private var job : Job? = null

    init {
        getMovies(_state.value.query,_state.value.page)
    }

    private fun getMovies(query : String, page : String){
        job?.cancel()

        job = getMovieUseCase.executeGetMovies(query = query, page = page).onEach {
            when(it){

                is Resource.Success -> {
                    _state.value = MoviesState(movies = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = MoviesState(error = it.message ?: "Error !")
                }

                is Resource.Loading -> {
                    _state.value = MoviesState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)

    }

    fun onEvent(event : MoviesEvent){
        when(event){
            is MoviesEvent.Search -> {
                getMovies(event.query, event.page)
            }
        }
    }

}