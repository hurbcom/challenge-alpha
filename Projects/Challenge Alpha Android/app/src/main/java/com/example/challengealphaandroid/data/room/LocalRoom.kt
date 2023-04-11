package com.example.challengealphaandroid.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.challengealphaandroid.model.Person
import com.example.challengealphaandroid.model.Planet
import com.example.challengealphaandroid.model.Starship

@Database(entities = [Person::class, Planet::class, Starship::class], version = 1)
abstract class LocalRoom : RoomDatabase() {

    abstract fun personDao(): PersonDao
    abstract fun planetDao(): PlanetDao
    abstract fun starshipDao(): StarshipDao

    companion object {

        private const val DB_NAME = "room-db"

        // For Singleton instantiation
        @Volatile
        private var instance: LocalRoom? = null

        fun getInstance(context: Context): LocalRoom {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): LocalRoom {
            return Room.databaseBuilder(
                context,
                LocalRoom::class.java, DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }

}