package com.barreto.android.data.database.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

class StringListTypeConverter {

    private val _moshi: Moshi
    private val _type: Type
    private val _adapter: JsonAdapter<List<String>>

    init {
        _moshi = Moshi.Builder().build()
        _type = Types.newParameterizedType(List::class.java, String::class.java)
        _adapter = _moshi.adapter(_type)
    }

    @TypeConverter
    fun stringToStringList(data: String?): List<String>? {

        if (data == null || data.isEmpty()) {
            return emptyList()
        }

        return _adapter.fromJson(data)
    }

    @TypeConverter
    fun stringListToJson(data: List<String>?): String {

        if (data == null || data.isEmpty()) {
            return ""
        }

        return _adapter.toJson(data)
    }
}