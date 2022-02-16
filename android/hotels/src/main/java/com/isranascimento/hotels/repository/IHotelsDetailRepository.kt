package com.isranascimento.hotels.repository

import com.isranascimento.coremodels.hotel.Hotel

interface IHotelsDetailRepository {
    suspend fun insertIntoLastViewed(hotel: Hotel)
}