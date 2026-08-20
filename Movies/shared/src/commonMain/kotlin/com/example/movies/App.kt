package com.example.movies

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movies.features.movies.MovieListRoute
import com.example.movies.navigation.AppRoutes
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration

@Composable
@Preview
fun App() {
    KoinApplication(
        configuration = koinConfiguration(
            declaration = {
                modules()
            }
        ), content = {
        MaterialTheme {
            val navController = rememberNavController()
            NavHost(navController, startDestination = AppRoutes.MovieList) {
                composable<AppRoutes.MovieList> {
                    MovieListRoute()
                }

                composable<AppRoutes.MovieDetails> {  }
            }
        }
    })


}