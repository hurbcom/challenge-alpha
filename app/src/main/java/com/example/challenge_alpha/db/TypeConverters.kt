package com.example.challenge_alpha.db

import androidx.room.TypeConverter
import java.util.*

    class ListStringConverter {
        companion object {
            @TypeConverter
            @JvmStatic
            fun fromListString(value: List<String>?): String? {
                var result = ""
                value?.forEachIndexed { index, element ->
                    result += element
                    if (index != (value.size - 1)) {
                        result += ","
                    }
                }
                return result
            }

            @TypeConverter
            @JvmStatic
            fun stringToListString(value: String?): List<String>? {
                val list = mutableListOf<String>()
                val array = value?.split(",")
                array?.forEach { list.add(it) }

                return list
            }
        }
    }

    class DateLongConverter {
        companion object {
            @TypeConverter
            @JvmStatic
            fun myDateToLong(value: Date) = value.time

            @TypeConverter
            @JvmStatic
            fun longToMyDate(value: Long) = Date(value)

        }
    }

