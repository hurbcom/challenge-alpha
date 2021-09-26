package com.filipeoliveira.hurbchallenge.data.remote

import android.content.res.AssetManager
import com.filipeoliveira.hurbchallenge.data.remote.model.HotelRequestResponse
import com.filipeoliveira.hurbchallenge.data.remote.model.HotelResponse
import com.google.gson.Gson

class HotelDataSourceImpl(
    val assetManager: AssetManager
) : HotelDataSource {

    override fun getHotelList(): List<HotelResponse> {
//        Em caso de requests sendo realmente feitos

//        val response = HotelClient.getHotelService().getUpComingMovies().execute()

//        return if (response.isSuccessful && response.body() != null) {
//            response.body()!!.result
//        } else {
//            emptyList()
//        }

//      Simulando uma resposta

        val gson = Gson()
        val response = gson.fromJson(
            assetManager.open("hotel.json")
                .bufferedReader()
                .use { it.readText() },
            HotelRequestResponse::class.java
        )

        return response.result
    }
}
