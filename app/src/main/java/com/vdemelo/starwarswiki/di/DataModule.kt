package com.vdemelo.starwarswiki.di

import com.vdemelo.starwarswiki.data.remote.NetworkConstants.BASE_URL
import com.vdemelo.starwarswiki.data.remote.api.StarWarsApi
import com.vdemelo.starwarswiki.data.repository.StarWarsLocalRepositoryImpl
import com.vdemelo.starwarswiki.data.repository.StarWarsRemoteRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

val dataModule = module {
    single {
        val logger = HttpLoggingInterceptor { Timber.d("API $it") }
        logger.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StarWarsApi::class.java)
    }
    single { StarWarsRemoteRepositoryImpl(get()) }
    single { StarWarsLocalRepositoryImpl() }
}
