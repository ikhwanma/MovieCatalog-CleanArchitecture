package com.example.moviecatalog.core.utils

import com.example.moviecatalog.core.data.source.local.entity.MovieEntity
import com.example.moviecatalog.core.data.source.remote.response.GetPopularMovieResponseItem
import com.example.moviecatalog.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<GetPopularMovieResponseItem>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                idMovie = it.id,
                nameMovie = it.originalTitle,
                imgMovie = it.posterPath,
                overview = it.overview,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.idMovie,
                originalTitle = it.nameMovie,
                posterPath = it.imgMovie,
                isFavorite = it.isFavorite,
                overview = it.overview
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        idMovie = input.id,
        nameMovie = input.originalTitle,
        imgMovie = input.posterPath,
        isFavorite = input.isFavorite,
        overview = input.overview
    )
}