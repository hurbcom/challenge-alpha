package com.filipeoliveira.hurbchallenge.data.local

import androidx.room.RoomDatabase
import com.filipeoliveira.hurbchallenge.data.local.model.HotelDB

class HotelLocalDataSourceImpl(
    private val database: RoomDatabase
) : HotelLocalDataSource {
    override fun getFavoriteHotels(): List<HotelDB> {
        return (database as Database).hotelDao().getAllFavoriteHotels()
    }

    override fun addToFavorites(hotel: HotelDB) {
        (database as Database).hotelDao().addToFavorites(hotel)
    }

    override fun removeFromFavorites(id: String) {
        (database as Database).hotelDao().deleteHotel(id)
    }

    override fun isFavorite(id: String): Boolean {
        return (database as Database).hotelDao().isFavorite(id)
    }


}
