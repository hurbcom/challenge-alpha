package com.isranascimento.hotels.repository

import com.isranascimento.hotels.models.Hotel

interface IHotelsDetailRepository {
    suspend fun insertIntoLastViewed(hotel: Hotel)
}