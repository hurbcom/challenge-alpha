package com.br.natanbrito.challenge.data.repository

import com.br.natanbrito.challenge.data.model.HotelNetworkResult


interface HotelsRepository {

    suspend fun getHotels(): HotelNetworkResult

}