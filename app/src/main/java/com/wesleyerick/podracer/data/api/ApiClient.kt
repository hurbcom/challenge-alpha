package com.wesleyerick.podracer.data.api

import com.wesleyerick.podracer.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getRetrofitInstance(): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

// fun getGenericService(retrofit: Retrofit): GenericService = retrofit.create(GenericService::class.java)
