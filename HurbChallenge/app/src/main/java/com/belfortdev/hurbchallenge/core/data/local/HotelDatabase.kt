package com.example.belfortdev.modernandroid.core.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.belfortdev.hurbchallenge.core.data.Converter
import com.example.belfortdev.modernandroid.core.model.SearchEntity

/**
 * Created by belfortdev on 27/09/18.
 */

@Database(entities = [SearchEntity.Hotel::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class HotelDatabase : RoomDatabase() {

    abstract fun hotelDao(): HotelDao

}