package com.wesleyerick.podracer.data.api

import com.wesleyerick.podracer.BuildConfig
import com.wesleyerick.podracer.data.service.StarshipsService
import com.wesleyerick.podracer.data.service.VehiclesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getRetrofitInstance(): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun getVehiclesService(retrofit: Retrofit): VehiclesService =
    retrofit.create(VehiclesService::class.java)
fun getStarshipsService(retrofit: Retrofit): StarshipsService =
    retrofit.create(StarshipsService::class.java)
