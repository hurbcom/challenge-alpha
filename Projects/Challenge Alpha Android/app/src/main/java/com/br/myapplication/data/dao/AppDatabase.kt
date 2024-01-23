package com.br.myapplication.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.br.myapplication.data.model.Film
import com.br.myapplication.data.model.Planet
import com.br.myapplication.data.model.Specie

@Database(entities = [Specie::class, Film::class, Planet::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun speciesDao(): SpeciesDao
    abstract fun filmDao(): FilmsDao
    abstract fun planetDao(): PlanetsDao

}
