package com.github.felipehjcosta.huchallenge.di

import com.github.felipehjcosta.huchallenge.base.hotels.SearchApi
import com.github.felipehjcosta.huchallenge.feature.hotels.test.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class TestNetworkModule {

    @Singleton
    @Provides
    fun providesHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(logging)
        }

    }.build()

    @Provides
    @Singleton
    fun providesMockWebServer(): MockWebServer = MockWebServer().apply { start() }

    @Singleton
    @Provides
    fun providesSearchApi(httpClient: OkHttpClient, mockWebServer: MockWebServer): SearchApi = Retrofit.Builder()
            .baseUrl("http://${mockWebServer.hostName}:${mockWebServer.port}")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build().create(SearchApi::class.java)
}