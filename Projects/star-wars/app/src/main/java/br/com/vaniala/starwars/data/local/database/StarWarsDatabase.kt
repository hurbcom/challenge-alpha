package br.com.vaniala.starwars.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.vaniala.starwars.data.local.dao.CategoryDao
import br.com.vaniala.starwars.data.local.dao.CharacterDao
import br.com.vaniala.starwars.data.local.dao.FilmDao
import br.com.vaniala.starwars.domain.model.Category
import br.com.vaniala.starwars.domain.model.Film
import br.com.vaniala.starwars.domain.model.People

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
@Database(
    entities = [
        Category::class,
        Film::class,
        People::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class StarWarsDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun filmDao(): FilmDao
    abstract fun characterDao(): CharacterDao
}
