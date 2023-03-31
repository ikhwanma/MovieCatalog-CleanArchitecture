package com.example.moviecatalog.view.detail

import androidx.lifecycle.ViewModel
import com.example.moviecatalog.core.domain.model.Movie
import com.example.moviecatalog.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun setFavorite(movie: Movie, state: Boolean) = movieUseCase.setFavoriteMovie(movie, state)
}