package com.example.challenge_alpha.db.favorites

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
abstract class FavoritesDataBase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao

    companion object {

        @Volatile
        private var INSTANCE: FavoritesDataBase? = null

        fun getInstance(context: Context): FavoritesDataBase =
            INSTANCE
                ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(
                        context
                    ).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FavoritesDataBase::class.java, "favorites.db"
        ).fallbackToDestructiveMigration().build()
    }
}


