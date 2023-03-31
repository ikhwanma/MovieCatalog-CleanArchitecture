package com.example.moviecatalog.core.domain.usecase

import com.example.moviecatalog.core.data.Resource
import com.example.moviecatalog.core.domain.model.Movie
import com.example.moviecatalog.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllPopularMovie(apiKey: String): Flow<Resource<List<Movie>>> =
        movieRepository.getAllPopularMovie(apiKey)

    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()


    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)
}