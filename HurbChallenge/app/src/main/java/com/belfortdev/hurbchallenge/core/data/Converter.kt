package com.belfortdev.hurbchallenge.core.data

import android.arch.persistence.room.TypeConverter
import com.belfortdev.hurbchallenge.core.model.SearchEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converter {

    @TypeConverter
    fun stringToAmenityList(data: String?): List<SearchEntity.Amenity> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<SearchEntity.Amenity>>() {

        }.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun amenityListToString(someObjects: List<SearchEntity.Amenity>): String {
        return Gson().toJson(someObjects)
    }

}