package com.example.moviecatalog.core.data.source.remote.network

import com.example.moviecatalog.core.data.source.remote.response.GetPopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key: String
    ): GetPopularMovieResponse
}