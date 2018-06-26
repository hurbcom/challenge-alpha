package br.com.loubake.challenge_hu.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HotelsService {
    @GET("api")
    fun listHotels(@Query("q") city: String): Call<HotelResults>
}