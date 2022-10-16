package com.example.moviedbapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviedbapp.repository.MoviesRepository
import com.example.moviedbapp.ui.movies.MoviesViewModel
import javax.inject.Inject

/**
Created By Fahad on 18/06/21
 */
class ViewModelFactory @Inject constructor(private val repository: BaseRepository , private val paramters: Any ?= null) :
    ViewModelProvider.Factory {
    // add your view models here
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
          //  example below
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> MoviesViewModel(repository   as MoviesRepository) as T
            else -> throw IllegalAccessException("Unknown View Model.Please add your view model in factory")
        }
    }
}