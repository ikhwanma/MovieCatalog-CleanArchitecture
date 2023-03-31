package com.example.moviecatalog.core.domain.repository

import com.example.moviecatalog.core.data.Resource
import com.example.moviecatalog.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllPopularMovie(apiKey: String): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}