package com.example.movies

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movies.data.di.dataModule
import com.example.movies.data.di.networkModule
import com.example.movies.data.di.viewModelModule
import com.example.movies.features.moviedetail.MovieDetailRoute
import com.example.movies.features.movies.MovieListRoute
import com.example.movies.features.theme.MoviesAppTheme
import com.example.movies.navigation.AppRoutes
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration

@Composable
@Preview
fun App() {
    KoinApplication(
        configuration = koinConfiguration(
            declaration = {
                modules(networkModule, dataModule, viewModelModule)
            }
        ), content = {
            MoviesAppTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = AppRoutes.MovieList) {
                    composable<AppRoutes.MovieList> {
                        MovieListRoute(
                            navigateToMovieDetail = { movieId ->
                                navController.navigate(AppRoutes.MovieDetails(movieId))
                            }
                        )
                    }
                    composable<AppRoutes.MovieDetails> {
                        MovieDetailRoute()
                    }
                }
            }
        }
    )
}