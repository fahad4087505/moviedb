package com.example.moviedbapp.repository

import com.example.moviedbapp.base.BaseRepository
import com.example.moviedbapp.data.response.movies.Movies
import com.example.moviedbapp.datasource.MoviesDataSource
import com.example.moviedbapp.network.Resource
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MoviesRepository @Inject constructor(private val moviesDataSource: MoviesDataSource) : BaseRepository() {

  suspend fun getMoviesList(hashMap: HashMap<String,String>): Flow<Resource<Movies>> {
    return flow {
      emit(Resource.loading())
      val result = moviesDataSource.getMoviesList(hashMap)
      emit(result)
    }.flowOn(Dispatchers.IO)
  }
}