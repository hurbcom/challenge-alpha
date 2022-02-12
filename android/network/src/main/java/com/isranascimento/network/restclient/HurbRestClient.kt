package com.isranascimento.network.restclient

import com.isranascimento.datatransferobjects.hotels.HotelsResponse
import retrofit2.Response
import retrofit2.http.GET

interface HurbRestClient {
    @GET("aka-godinez/challenge-alpha/master/examples/hotel.json")
    suspend fun getHotelsList(): Response<HotelsResponse>
}