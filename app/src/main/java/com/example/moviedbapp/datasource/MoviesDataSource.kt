package com.example.moviedbapp.datasource
import com.example.moviedbapp.apiservice.MovieApiService
import com.example.moviedbapp.base.BaseResponse
import javax.inject.Inject

class MoviesDataSource @Inject constructor
  (private val movieApiService: MovieApiService): BaseResponse() {
    suspend fun getMoviesList(hashMap: HashMap<String,String>) = getResult {
      movieApiService.getMoviesList(hashMap)
    }
}