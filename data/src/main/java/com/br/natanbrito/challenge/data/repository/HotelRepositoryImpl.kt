package com.br.natanbrito.challenge.data.repository

import com.br.natanbrito.challenge.data.datasource.HotelDataSource
import com.br.natanbrito.challenge.data.model.Hotel
import com.br.natanbrito.challenge.data.model.HotelNetworkResult
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor(private val hotelDataSource: HotelDataSource): HotelsRepository {
    override suspend fun getHotels(): HotelNetworkResult = hotelDataSource.fetchHotels()
}