package com.filipeoliveira.hurbchallenge.data.remote

import android.content.res.AssetManager
import com.filipeoliveira.hurbchallenge.data.remote.model.HotelsInfoResponse
import com.filipeoliveira.hurbchallenge.data.remote.model.HotelResponse
import com.google.gson.Gson

class HotelDataSourceImpl(
    val assetManager: AssetManager
) : HotelDataSource {

    override fun getHotelList(query: String, enabledFilters: List<String>): HotelsInfoResponse {
//        Em caso de requests sendo realmente feitos

//        val response = HotelClient.getHotelService().getHotels().execute()

//        return if (response.isSuccessful && response.body() != null) {
//            response.body()!!.result
//        } else {
//            emptyList()
//        }

//      Simulando uma resposta
        val gson = Gson()
        val request = gson.fromJson(
            assetManager.open("hotel.json")
                .bufferedReader()
                .use { it.readText() },
            HotelsInfoResponse::class.java
        )
        var result = request.result

//        Simulando uma chamada na API utilizando uma query
        if (query.isNotEmpty()) {
            result = result.filter {
                matchCityName(query, it) ||
                        matchStateName(query, it) ||
                        matchCountryName(query, it)
            }
        }

//        Simulando uma chamada na API utilizando filtros
        if (enabledFilters.isNotEmpty()) {
            result = result.filter {
                hasAllAmenitiesFromFilter(it, enabledFilters)
            }
        }

        return HotelsInfoResponse(result = result, filters = request.filters)
    }

    private fun hasAllAmenitiesFromFilter(
        hotelResponse: HotelResponse,
        enabledFilters: List<String>
    ): Boolean {
        if (hotelResponse.amenities == null) return false

        for (amenity in enabledFilters){
            val isAmenityMissing = hotelResponse.amenities.firstOrNull{
                it.name == amenity
            } == null

            if (isAmenityMissing) return false
        }

        return true
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
