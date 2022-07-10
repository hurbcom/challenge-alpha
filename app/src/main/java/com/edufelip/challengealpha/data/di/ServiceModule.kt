package com.edufelip.challengealpha.data.di

import com.edufelip.challengealpha.BuildConfig
import com.edufelip.challengealpha.data.network.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesBackendApi(factory: GsonConverterFactory): ApiService {
        val baseUrl: String = BuildConfig.BASE_URL
        val logging = HttpLoggingInterceptor()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.NANOSECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build()
            )
            .addConverterFactory(factory)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}