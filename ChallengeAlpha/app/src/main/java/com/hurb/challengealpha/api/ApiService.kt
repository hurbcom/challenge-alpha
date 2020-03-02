package com.hurb.challengealpha.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val gson: Gson = GsonBuilder().create()

val apiWithInterceptor: HurbApi = Retrofit.Builder()
    .baseUrl(HurbApi.API_URL)
    .client(
        OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()
    )
    .addConverterFactory(GsonConverterFactory.create(gson))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()
    .create(HurbApi::class.java)

val api: HurbApi = Retrofit.Builder()
    .baseUrl(HurbApi.API_URL)
    .client(OkHttpClient.Builder().build())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()
    .create(HurbApi::class.java)