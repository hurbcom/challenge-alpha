package com.br.natanbrito.challenge.data.datasource

import com.br.natanbrito.challenge.data.api.HurbApi
import com.br.natanbrito.challenge.data.model.HotelNetworkResult
import javax.inject.Inject

class HotelDataSourceImpl @Inject constructor(
    private val hurbApi: HurbApi
) : HotelDataSource {

    override suspend fun fetchHotels(): HotelNetworkResult {

        var result: HotelNetworkResult

        val response = hurbApi.getHotels()

        if (response.isSuccessful) {

            result = if (response.body() != null) {
                HotelNetworkResult.Success(response.body()!!)
            } else {
                HotelNetworkResult.Error("")
            }

        } else {
            result = HotelNetworkResult.Error(response.errorBody().toString())
        }

        return result
    }

}