package com.example.movies.features.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movies.data.repository.MoviesRepository
import com.example.movies.domain.model.MovieSection
import com.example.movies.domain.model.movie1
import movies.shared.generated.resources.Res
import movies.shared.generated.resources.movies_list_popular_movies
import movies.shared.generated.resources.movies_list_top_rated_movies
import movies.shared.generated.resources.movies_list_upcoming_movies
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import com.example.movies.features.components.MovieSection as MovieSectionComponent

@Composable
fun MovieListRoute(
    viewModel: MovieListViewModel = koinViewModel()
) {

    val movieListState by viewModel.moviesListState.collectAsStateWithLifecycle()

    MovieListScreen(
        moviesListState = movieListState
    )
}


@Composable
fun MovieListScreen(
    moviesListState: MoviesListState,
) {
    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            when (moviesListState) {
                MoviesListState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                is MoviesListState.Success -> {
                    LazyColumn(
                        modifier = Modifier,
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(32.dp)
                    ) {
                        items(moviesListState.movieSection) { movieSection ->
                            val title = when (movieSection.section) {
                                MovieSection.SectionType.POPULAR -> stringResource(Res.string.movies_list_popular_movies)
                                MovieSection.SectionType.TOP_RATED -> stringResource(Res.string.movies_list_top_rated_movies)
                                MovieSection.SectionType.UPCOMING -> stringResource(Res.string.movies_list_upcoming_movies)
                            }

                            MovieSectionComponent(
                                title = title,
                                movies = movieSection.movies
                            )
                        }
                    }
                }
                is MoviesListState.Error -> {
                    Text(
                        text = moviesListState.message,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieListScreenPreview() {
    MovieListScreen(
        moviesListState = MoviesListState.Success(
            movieSection = listOf(
                MovieSection(
                    section = MovieSection.SectionType.POPULAR,
                    movies = List(10) { movie1 }
                ),
                MovieSection(
                    section = MovieSection.SectionType.TOP_RATED,
                    movies = List(10) { movie1 }
                ),
                MovieSection(
                    section = MovieSection.SectionType.UPCOMING,
                    movies = List(10) { movie1 }
                )
            )
        )
    )
}