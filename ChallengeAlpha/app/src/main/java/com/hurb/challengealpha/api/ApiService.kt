package com.hurb.challengealpha.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val gson: Gson = GsonBuilder().create()

val api: HurbApi = Retrofit.Builder()
    .baseUrl(HurbApi.API_URL)
    .client(OkHttpClient.Builder().build())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()
    .create(HurbApi::class.java)