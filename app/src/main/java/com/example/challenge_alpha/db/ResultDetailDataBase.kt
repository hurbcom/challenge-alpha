package com.example.challenge_alpha.db

import android.content.Context
import androidx.room.*
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities
import com.example.challenge_alpha.model.ResultDetailGallery
import java.util.*

@Database(
    entities = [ResultDetail::class, ResultDetailAmenities::class, ResultDetailGallery::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(ListStringConverter::class)
abstract class ResultDetailDataBase : RoomDatabase() {

    abstract fun resultDetailDao(): ResultDetailDao

    companion object {

        @Volatile
        private var INSTANCE: ResultDetailDataBase? = null

        fun getInstance(context: Context): ResultDetailDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ResultDetailDataBase::class.java, "results.db"
        ).fallbackToDestructiveMigration().build()
    }
}

class ListStringConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromListString(value: List<String>?): String? {
            var result = ""
            value?.forEachIndexed { index, element ->
                result += element
                if(index != (value.size-1)){
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

