package br.com.mdr.starwars.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.mdr.starwars.common.Constants.APP_DATABASE
import br.com.mdr.starwars.data.local.dao.FilmDao
import br.com.mdr.starwars.data.local.dao.FilmRemoteKeysDao
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.model.FilmRemoteKeys

@Database(entities = [Film::class, FilmRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): AppDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            } else {
                Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE)
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    abstract fun getFilmDao(): FilmDao

    abstract fun getRemoteKeysDao(): FilmRemoteKeysDao
}