package com.filipeoliveira.hurbchallenge.data.remote

import com.filipeoliveira.hurbchallenge.data.remote.model.HotelsInfoResponse
import retrofit2.Call
import retrofit2.http.GET

interface HotelService {
    @GET("/endpoint/desejado")
    fun getHotels(): Call<HotelsInfoResponse>
}