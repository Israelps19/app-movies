package com.example.movies.data.di

import com.example.movies.features.moviedetail.MovieDetailViewModel
import com.example.movies.features.movies.MovieListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MovieListViewModel(get())
    }
    viewModel {
        MovieDetailViewModel(get(), get())
    }
}