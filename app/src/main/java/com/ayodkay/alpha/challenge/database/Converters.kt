package com.ayodkay.alpha.challenge.database

import androidx.lifecycle.LiveData
import androidx.room.TypeConverter
import com.ayodkay.alpha.challenge.database.Hotels
import com.ayodkay.alpha.challenge.model.Address
import com.ayodkay.alpha.challenge.model.Amenities
import com.ayodkay.alpha.challenge.model.Descriptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String): LiveData<ArrayList<Hotels>> {
        val listType = object : TypeToken<LiveData<ArrayList<Hotels>>>() {

        }.type
        return Gson().fromJson<LiveData<ArrayList<Hotels>>>(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: LiveData<ArrayList<Hotels>>): String {
        val gson = Gson()
        return gson.toJson(list)
    }


    @TypeConverter
    fun fromAmennityString(value: String): ArrayList<ArrayList<Amenities>> {
        val listType = object : TypeToken<ArrayList<ArrayList<Amenities>>>() {

        }.type
        return Gson().fromJson<ArrayList<ArrayList<Amenities>>>(value, listType)
    }

    @TypeConverter
    fun fromArrayListofArrayList(list: ArrayList<ArrayList<Amenities>>): String {
        val gson = Gson()
        return gson.toJson(list)
    }


    @TypeConverter
    fun fromAddressString(value: String): Address {
        val listType = object : TypeToken<Address>() {

        }.type
        return Gson().fromJson<Address>(value, listType)
    }

    @TypeConverter
    fun fromAddress(list: Address): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromDescriptionString(value: String): Descriptions {
        val listType = object : TypeToken<Descriptions>() {

        }.type
        return Gson().fromJson<Descriptions>(value, listType)
    }

    @TypeConverter
    fun fromDescription(list: Descriptions): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromArrayListofArrayListString(value: String): ArrayList<ArrayList<String>> {
        val listType = object : TypeToken<ArrayList<ArrayList<String>>>() {

        }.type
        return Gson().fromJson<ArrayList<ArrayList<String>>>(value, listType)
    }

    @TypeConverter
    fun fromArrayListofArrayListString(list: ArrayList<ArrayList<String>>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}