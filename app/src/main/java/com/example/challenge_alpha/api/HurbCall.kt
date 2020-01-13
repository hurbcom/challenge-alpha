package com.example.challenge_alpha.api

import android.util.Log
import com.example.challenge_alpha.model.Filters
import com.example.challenge_alpha.model.Header
import com.example.challenge_alpha.model.ResultDetail
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface HurbService {

    @GET("search/api/suggestion")
    suspend fun suggestion(
        @Query("q") suggestion: String
    ): Response<HurbSuggestions>

    @GET("search/api?")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int
    ): Response<HurbResponse>

    companion object {
        private const val BASE_URL = "https://www.hurb.com/"

        fun create(): HurbService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HurbService::class.java)
        }
    }
}