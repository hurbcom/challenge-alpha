package com.ayodkay.alpha.challenge.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class Converters {
    @TypeConverter
    fun fromString(value: String): JSONObject{
        val listType = object : TypeToken<ArrayList<String>>() {

        }.type
        return Gson().fromJson<JSONObject>(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: JSONObject): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}