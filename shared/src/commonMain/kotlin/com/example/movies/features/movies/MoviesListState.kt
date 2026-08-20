package com.example.movies.features.movies

import com.example.movies.domain.model.MovieSection

interface MoviesListState {
    data object Loading : MoviesListState
    data class Success(val movieSection: List<MovieSection>) : MoviesListState
    data class Error(val message: String) : MoviesListState
}