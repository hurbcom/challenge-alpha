package com.filipeoliveira.hurbchallenge.data.local.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HotelDAO {
    @Insert
    fun addToFavorites(hotelDB: HotelDB)

    @Query("DELETE FROM HotelDB where id == :id")
    fun deleteHotel(id: String)

    @Query("SELECT * FROM HotelDB")
    fun getAllFavoriteHotels() : List<HotelDB>

    @Query("SELECT EXISTS (SELECT * FROM HotelDB WHERE id == :id)")
    fun isFavorite(id: String) : Boolean
}