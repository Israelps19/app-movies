package com.example.movies.data.network

import com.example.movies.data.network.model.MoviesListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://api.themoviedb.org"
const val IMAGE_SMALL_BASE_URL = "https://image.tmdb.org/t/p/w154"

class KtorApiClient {

    private val client = HttpClient {
        expectSuccess = true
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZTcyYzNmYjQ5MDFjZDI0OWFlMTY2YTkyN2FhMWU3NyIsIm5iZiI6MTY4NzEzMDc5Ni4yNDk5OTk4LCJzdWIiOiI2NDhmOTJhYzQ1NzY1ZDAxMWRiZmM3YjAiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.KjWdp-cZy1kEhg1-DYAUIqlcZ5NZicYRvPiJ9bjn23A", "")
                }
            }
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }
    }

    suspend fun getMovies(category: String, language: String = "pt-BR"): MoviesListResponse {
        return client.get("$BASE_URL/3/movie/$category") {
            parameter("language", language)
        }.body()
    }
}