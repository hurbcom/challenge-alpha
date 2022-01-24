package com.br.natanbrito.challenge.data.datasource

import android.util.Log
import com.br.natanbrito.challenge.data.api.HurbApi
import com.br.natanbrito.challenge.data.model.Hotel
import javax.inject.Inject

class HotelDataSourceImpl @Inject constructor(
    private val hurbApi: HurbApi
) : HotelDataSource {

    override suspend fun fetchHotels(): Hotel? {

        var result: Hotel? = null

        val response = hurbApi.getHotels()

        if (response.isSuccessful) {
            result = response.body()
            Log.d("NATAN", "ali = ${result?.results?.size}")
        } else {
            Log.d("NATAN", "ali = ${response.errorBody()}")
        }

        return result
    }

}