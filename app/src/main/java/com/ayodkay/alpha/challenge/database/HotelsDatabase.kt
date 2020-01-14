package com.ayodkay.alpha.challenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Hotels::class], version = 1)
abstract class HotelsDatabase : RoomDatabase() {

    abstract fun hotelsDao(): HotelsDao

    companion object {
        @Volatile
        private var INSTANCE: HotelsDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): HotelsDatabase {
            return INSTANCE


                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        HotelsDatabase::class.java,
                        "hotel_table"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(
                            HotelDatabaseCallback(
                                scope
                            )
                        )
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
        }

        private class HotelDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch {
                        database.hotelsDao()
                    }
                }
            }
        }
    }

}