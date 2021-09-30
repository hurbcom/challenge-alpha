package com.filipeoliveira.hurbchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.filipeoliveira.hurbchallenge.data.local.model.HotelDAO
import com.filipeoliveira.hurbchallenge.data.local.model.HotelDB

@Database(entities = [HotelDB::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun hotelDao() : HotelDAO
}