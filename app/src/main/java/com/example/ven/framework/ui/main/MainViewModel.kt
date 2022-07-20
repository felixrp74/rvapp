package com.example.ven.framework.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ven.domain.Movie
import com.example.ven.framework.usecases.LoadPopularMovies
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val loadPopularMovies: LoadPopularMovies

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisibility: LiveData<Boolean> get() = _progressVisible

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _showMessage = MutableLiveData<Boolean>()
    val showMessage: LiveData<Boolean> get() = _showMessage

    fun onCreate() {
        viewModelScope.launch {
            _progressVisible.value = true
            _movies.value = loadPopularMovies.invoke()
            _progressVisible.value = false

        }
    }

    fun onMovieClicked(movie: Movie){
        showMessage.value = movie.title
    }

}