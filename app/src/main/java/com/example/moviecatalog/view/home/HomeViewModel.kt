package com.example.moviecatalog.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.moviecatalog.core.domain.usecase.MovieUseCase

class HomeViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun getAllPopularMovie(apiKey: String) =
        movieUseCase.getAllPopularMovie(apiKey).asLiveData()
}