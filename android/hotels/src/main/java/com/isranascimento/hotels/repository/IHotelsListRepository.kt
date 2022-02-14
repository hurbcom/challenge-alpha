package com.isranascimento.hotels.repository

import com.isranascimento.hotels.models.Hotel
import com.isranascimento.hotels.models.HotelsListDomainState

interface IHotelsListRepository {
    suspend fun getHotelList(): HotelsListDomainState
    fun getHotelWithId(hotelId: String): Hotel?
}