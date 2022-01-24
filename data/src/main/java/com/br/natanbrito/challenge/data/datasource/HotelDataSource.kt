package com.br.natanbrito.challenge.data.datasource

import com.br.natanbrito.challenge.data.model.Hotel


interface HotelDataSource {

    suspend fun fetchHotels(): Hotel?

}