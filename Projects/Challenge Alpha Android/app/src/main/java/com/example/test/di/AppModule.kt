package com.example.test.di

import com.example.test.BuildConfig
import com.example.test.data.datasources.network.StarWarsApi
import com.example.test.data.datasources.network.StarWarsMockApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
    }

    @Singleton
    @Provides
    fun providesHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(BuildConfig.API_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(BuildConfig.API_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit): StarWarsApi {
        return if (BuildConfig.BUILD_TYPE.contentEquals("mock")) StarWarsMockApi()
        else retrofit.create(StarWarsApi::class.java)
    }
}