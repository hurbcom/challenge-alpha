package com.isranascimento.hotels.repository

import com.isranascimento.core.models.hotel.Hotel

interface IHotelsDetailRepository {
    suspend fun insertIntoLastViewed(hotel: Hotel)
}