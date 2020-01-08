package com.example.challenge_alpha.db.resultDetail

import android.content.Context
import androidx.room.*
import com.example.challenge_alpha.db.DateLongConverter
import com.example.challenge_alpha.db.ListStringConverter
import com.example.challenge_alpha.model.*

@Database(
    entities = [ResultDetail::class, ResultDetailAmenities::class, ResultDetailGallery::class, ResultDetailTaxes::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListStringConverter::class, DateLongConverter::class)
abstract class ResultDetailDataBase : RoomDatabase() {

    abstract fun resultDetailDao(): ResultDetailDao

    companion object {

        @Volatile
        private var INSTANCE: ResultDetailDataBase? = null

        fun getInstance(context: Context): ResultDetailDataBase =
            INSTANCE
                ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(
                        context
                    ).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ResultDetailDataBase::class.java, "results.db"
        ).fallbackToDestructiveMigration().build()
    }
}


