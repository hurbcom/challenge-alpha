package com.github.felipehjcosta.huchallenge.base.di

import com.github.felipehjcosta.huchallenge.base.BuildConfig
import com.github.felipehjcosta.huchallenge.base.hotels.SearchApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://search.hotelurbano.com/"

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            addInterceptor(logging)
        }
    }.build()

    @Singleton
    @Provides
    fun providesSearchApi(httpClient: OkHttpClient): SearchApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build().create(SearchApi::class.java)
}