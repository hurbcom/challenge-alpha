package com.edufelip.challengealpha.data.di

import android.content.Context
import androidx.room.Room
import com.edufelip.challengealpha.BuildConfig
import com.edufelip.challengealpha.data.local.room.FavoriteDatabase
import com.edufelip.challengealpha.data.local.room.FavoriteDatabase.Companion.DATABASE_NAME
import com.edufelip.challengealpha.data.network.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
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

    @Singleton
    @Provides
    fun providesFavoritesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, FavoriteDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providesFavoritesDao(
        database: FavoriteDatabase
    ) = database.favoriteDao()
}