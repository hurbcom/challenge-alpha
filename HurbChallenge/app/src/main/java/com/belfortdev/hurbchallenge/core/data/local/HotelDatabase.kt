package com.belfortdev.hurbchallenge.core.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.belfortdev.hurbchallenge.core.data.Converter
import com.belfortdev.hurbchallenge.core.model.SearchEntity

@Database(entities = [SearchEntity.Hotel::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class HotelDatabase : RoomDatabase() {

    abstract fun hotelDao(): HotelDao

}