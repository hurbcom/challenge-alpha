package com.vdemelo.starwarswiki.di

import com.vdemelo.starwarswiki.data.api.StarWarsApi
import com.vdemelo.starwarswiki.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

//TODO - colocar em data.network?
val networkModule = module {
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
}
