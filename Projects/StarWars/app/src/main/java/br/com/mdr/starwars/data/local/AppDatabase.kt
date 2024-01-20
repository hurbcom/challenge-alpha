package br.com.mdr.starwars.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.mdr.starwars.data.local.dao.CharacterRemoteKeysDao
import br.com.mdr.starwars.data.local.dao.CharactersDao
import br.com.mdr.starwars.data.local.dao.FilmDao
import br.com.mdr.starwars.data.local.dao.FilmRemoteKeysDao
import br.com.mdr.starwars.data.local.dao.LastSeenDao
import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.domain.model.CharacterRemoteKeys
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.model.FilmRemoteKeys
import br.com.mdr.starwars.domain.model.LastSeen

@Database(
    entities =
    [
        Film::class, FilmRemoteKeys::class, Character::class,
        CharacterRemoteKeys::class, LastSeen::class
    ],
    version = 1
)
@TypeConverters(DatabaseConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getFilmDao(): FilmDao
    abstract fun getFilmKeysDao(): FilmRemoteKeysDao
    abstract fun getCharactersDao(): CharactersDao
    abstract fun getCharacterKeysDao(): CharacterRemoteKeysDao
    abstract fun getLastSeenDao(): LastSeenDao
}
