package br.com.hurbandroidchallenge.data.local.dao

import androidx.room.*
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity

@Dao
interface StarWarsBookDao {

    @Upsert
    suspend fun upsertAllCategories(homeCategoriesEntity: List<HomeCategoriesEntity>)

    @Upsert
    suspend fun upsertAllPeople(peopleEntity: List<PeopleEntity>)

    @Upsert
    suspend fun upsertAllFilms(filmsEntity:  List<FilmEntity>)

    @Query("DELETE FROM categories")
    suspend fun deleteAllCategories()

    @Query("DELETE FROM people")
    suspend fun deleteAllPeople()

    @Query("DELETE FROM films")
    suspend fun deleteAllFilms()

    @Query("SELECT * FROM categories")
    suspend fun getCategories(): List<HomeCategoriesEntity>

    @Query("SELECT * FROM people")
    suspend fun getCharacters(): List<PeopleEntity>

    @Query("SELECT * FROM films")
    suspend fun getFilms(): List<FilmEntity>

}