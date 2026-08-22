package com.example.movies.features.moviedetail

import com.example.movies.domain.model.Movie

interface MovieDetailState {
    data object Loading : MovieDetailState
    data class Success(val movie: Movie) : MovieDetailState
    data class Error(val message: String) : MovieDetailState
}