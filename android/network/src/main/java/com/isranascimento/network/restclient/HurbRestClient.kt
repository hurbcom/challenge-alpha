package com.isranascimento.network.restclient

import com.isranascimento.networkdtos.hotels.HotelsResponse
import retrofit2.Response
import retrofit2.http.GET

interface HurbRestClient {
    @GET("hurbcom/challenge-alpha/master/examples/hotel.json")
    suspend fun getHotelsList(): Response<HotelsResponse>
}