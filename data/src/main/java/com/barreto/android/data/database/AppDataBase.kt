package com.barreto.android.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.barreto.android.data.content.local.dao.ContentItemDao
import com.barreto.android.data.content.local.entity.ContentItemDB
import com.barreto.android.data.database.converter.StringListTypeConverter

@Database(entities = [(ContentItemDB::class)], version = 1)
@TypeConverters(
    StringListTypeConverter::class
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun contentItemDao(): ContentItemDao
}
//
//val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        try {
//            database.execSQL("ALTER TABLE contents ADD COLUMN uid INTEGER NOT NULL DEFAULT 0")
//        } catch (error: Exception) { }
//    }
//}