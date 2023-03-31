package com.example.moviecatalog.di

import com.example.moviecatalog.core.domain.usecase.MovieInteractor
import com.example.moviecatalog.core.domain.usecase.MovieUseCase
import com.example.moviecatalog.view.detail.DetailViewModel
import com.example.moviecatalog.view.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase>{ MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}