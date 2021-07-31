package com.example.pedulidigital

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pedulidigital.data.MovieRepository
import com.example.pedulidigital.module.Movie
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private var repository: MovieRepository = MovieRepository()

    val moviesResultLiveData = MutableLiveData<Result<List<Movie>>>()
    val moviesLiveData = MutableLiveData<List<Movie>>()

    val errorMessageLiveData = MutableLiveData<String>()

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            moviesResultLiveData.value = repository.fetchNowPlayingMovies()
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            if (query.length >= 3) {
                moviesResultLiveData.value = repository.searchMovies(query)
            }
        }
    }

    fun handleMovieListResponse(result: Result<List<Movie>>) {
        if (result.isSuccess) {
            moviesLiveData.value = result.getOrDefault(emptyList())
        } else {
            errorMessageLiveData.value = result.exceptionOrNull()?.message.orEmpty()
        }
    }
}