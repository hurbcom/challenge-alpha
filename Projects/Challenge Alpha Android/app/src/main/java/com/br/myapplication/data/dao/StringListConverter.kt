package com.br.myapplication.data.dao

import androidx.room.TypeConverter

class StringListConverter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",").map { it.trim() }
    }

    @TypeConverter
    fun toString(value: List<String>): String {
        return value.joinToString(",")
    }
}