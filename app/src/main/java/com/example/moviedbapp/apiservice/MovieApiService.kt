package com.example.moviedbapp.apiservice

import com.example.moviedbapp.data.response.movies.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.HashMap

interface MovieApiService {
  @GET("search/multi")
  suspend fun getMoviesList(@QueryMap hashMap: HashMap<String,String> ): Response<Movies>
}