package com.example.movies.data.di

import com.example.movies.data.network.KtorApiClient
import org.koin.dsl.module

val networkModule = module {
    single {
        KtorApiClient()
    }
}