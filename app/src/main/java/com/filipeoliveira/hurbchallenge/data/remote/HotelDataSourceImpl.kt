package com.filipeoliveira.hurbchallenge.data.remote

import android.content.res.AssetManager
import com.filipeoliveira.hurbchallenge.data.remote.model.HotelRequestResponse
import com.filipeoliveira.hurbchallenge.data.remote.model.HotelResponse
import com.google.gson.Gson

class HotelDataSourceImpl(
    val assetManager: AssetManager
) : HotelDataSource {

    override fun getHotelList(query: String): List<HotelResponse> {
//        Em caso de requests sendo realmente feitos

//        val response = HotelClient.getHotelService().getHotels().execute()

//        return if (response.isSuccessful && response.body() != null) {
//            response.body()!!.result
//        } else {
//            emptyList()
//        }

//      Simulando uma resposta
        val gson = Gson()
        var response = gson.fromJson(
            assetManager.open("hotel.json")
                .bufferedReader()
                .use { it.readText() },
            HotelRequestResponse::class.java
        ).result

//        Simulando uma chamada na API utilizando uma query
        if (query.isNotEmpty()) {
            response = response.filter {
                matchCityName(query, it) ||
                matchStateName(query, it) ||
                matchCountryName(query, it)
            }
        }

        return response
    }

    private fun matchCityName(query: String, hotelResponse: HotelResponse): Boolean {
        val city = hotelResponse.address?.city

        return city?.contains(query, true) ?: false
    }

    private fun matchStateName(query: String, hotelResponse: HotelResponse): Boolean {
        val state = hotelResponse.address?.state

        return state?.contains(query, true) ?: false
    }

    private fun matchCountryName(query: String, hotelResponse: HotelResponse): Boolean {
        val country = hotelResponse.address?.country

        return country?.contains(query, true) ?: false
    }
}
