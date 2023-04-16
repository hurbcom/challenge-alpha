package br.com.hurbandroidchallenge.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.hurbandroidchallenge.data.local.dao.CharactersDao
import br.com.hurbandroidchallenge.data.local.dao.FilmsDao
import br.com.hurbandroidchallenge.data.local.dao.PlanetsDao
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity
import br.com.hurbandroidchallenge.data.local.model.PlanetEntity
import br.com.hurbandroidchallenge.data.local.type_converter.StringListConverter

@Database(
    entities = [
        PeopleEntity::class,
        FilmEntity::class,
        PlanetEntity::class
    ],
    version = 1
)
@TypeConverters(StringListConverter::class)
abstract class StarWarsBookDatabase : RoomDatabase() {

    abstract val planetsDao: PlanetsDao
    abstract val charactersDao: CharactersDao
    abstract val filmsDao: FilmsDao

}