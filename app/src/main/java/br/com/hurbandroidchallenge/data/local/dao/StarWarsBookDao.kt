package br.com.hurbandroidchallenge.data.local.dao

import androidx.room.*
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity

@Dao
interface StarWarsBookDao {

    @Insert
    suspend fun insertNewCategories(homeCategoriesEntity: List<HomeCategoriesEntity>)

    @Insert
    suspend fun insertNewCharacters(peopleEntity: List<PeopleEntity>)

    @Insert
    suspend fun insertNewFilms(filmsEntity:  List<FilmEntity>)

    @Query("DELETE FROM categories")
    suspend fun deleteAllCategories()

    @Query("DELETE FROM people")
    suspend fun deleteAllCharacters()

    @Query("DELETE FROM films")
    suspend fun deleteAllFilms()

    @Query("SELECT * FROM categories")
    suspend fun getCategories(): List<HomeCategoriesEntity>

    @Query("SELECT * FROM people")
    suspend fun getCharacters(): List<PeopleEntity>

    @Query("SELECT * FROM people WHERE id = :id")
    suspend fun getCharacterById(id: Int): PeopleEntity

    @Query("SELECT * FROM films")
    suspend fun getFilms(): List<FilmEntity>

    @Query("SELECT * FROM films WHERE id = :id")
    suspend fun getFilmById(id: Int): FilmEntity

    @Query("SELECT COUNT(1) FROM people WHERE id = :id")
    suspend fun containsCharacter(id: Int): Int

    @Query("SELECT COUNT(1) FROM films WHERE id = :id")
    suspend fun containsFilm(id: Int): Int

}