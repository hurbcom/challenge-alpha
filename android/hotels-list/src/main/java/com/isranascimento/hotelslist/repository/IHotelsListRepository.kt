package com.isranascimento.hotelslist.repository

import com.isranascimento.hotelslist.models.Hotel
import com.isranascimento.hotelslist.models.HotelsListDomainState

interface IHotelsListRepository {
    suspend fun getHotelList(): HotelsListDomainState
    fun getHotelWithSku(hotelId: String): Hotel?
}