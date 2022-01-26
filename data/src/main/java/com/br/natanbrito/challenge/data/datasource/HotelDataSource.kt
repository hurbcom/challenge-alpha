package com.br.natanbrito.challenge.data.datasource

import com.br.natanbrito.challenge.data.model.HotelNetworkResult


interface HotelDataSource {

    suspend fun fetchHotels(): HotelNetworkResult

}