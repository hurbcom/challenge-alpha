package com.example.challenge_alpha.db.lastSeen

import android.content.Context
import androidx.room.*
import com.example.challenge_alpha.db.DateLongConverter
import com.example.challenge_alpha.db.ListStringConverter
import com.example.challenge_alpha.db.resultDetail.ResultDetailDao
import com.example.challenge_alpha.model.*

@Database(
    entities = [ResultDetail::class, ResultDetailAmenities::class, ResultDetailGallery::class, ResultDetailTaxes::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListStringConverter::class, DateLongConverter::class)
abstract class LastSeenDataBase : RoomDatabase() {

    abstract fun lastSeenDao(): LastSeenDao

    companion object {

        @Volatile
        private var INSTANCE: LastSeenDataBase? = null

        fun getInstance(context: Context): LastSeenDataBase =
            INSTANCE
                ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(
                        context
                    ).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            LastSeenDataBase::class.java, "lastseen.db"
        ).fallbackToDestructiveMigration().build()
    }
}


