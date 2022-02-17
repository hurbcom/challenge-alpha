package com.isranascimento.hoteldetail.repository

import com.isranascimento.coremodels.hotel.Hotel

interface IHotelsDetailRepository {
    suspend fun insertIntoLastViewed(hotel: Hotel)
}