package com.example.moviedbapp.ui.movies
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedbapp.data.response.movies.Movies
import com.example.moviedbapp.network.Resource
import com.example.moviedbapp.repository.MoviesRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesViewModel @ViewModelInject constructor(private val repository: MoviesRepository) : ViewModel() {

     val _moviesMutableList = MutableLiveData<Resource<Movies>>()

    fun fetchMoviesList(hashMap: HashMap<String,String>) {
        viewModelScope.launch {
            repository.getMoviesList(hashMap).collect {
                _moviesMutableList.value = it
            }
        }
    }
}