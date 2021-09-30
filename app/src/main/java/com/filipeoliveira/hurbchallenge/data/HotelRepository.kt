package com.filipeoliveira.hurbchallenge.data

import com.filipeoliveira.hurbchallenge.ui.model.HotelInfoUI
import com.filipeoliveira.hurbchallenge.ui.model.HotelUI

interface HotelRepository {
    fun getHotelList(query: String, enabledFilters: List<String>): HotelInfoUI
    fun getFavoriteHotels(): List<HotelUI>
    fun addToFavorites(hotelUI: HotelUI)
    fun removeFromFavorites(hotelUI: HotelUI)
    fun isFavorite(hotelUI: HotelUI) : Boolean
}