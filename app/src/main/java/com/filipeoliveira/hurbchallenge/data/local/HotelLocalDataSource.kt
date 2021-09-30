package com.filipeoliveira.hurbchallenge.data.local

import com.filipeoliveira.hurbchallenge.data.local.model.HotelDB

interface HotelLocalDataSource {
    fun getFavoriteHotels(): List<HotelDB>
    fun addToFavorites(hotel: HotelDB)
    fun removeFromFavorites(hotel: String)
    fun isFavorite(hotel: String) : Boolean
}
