package com.br.natanbrito.challenge.data.api

import com.br.natanbrito.challenge.data.model.Hotel
import retrofit2.Response
import retrofit2.http.GET

interface HurbApi {

    @GET("hotel.json")
    suspend fun getHotels(): Response<Hotel>

}