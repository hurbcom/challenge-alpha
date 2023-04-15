package br.com.hurbandroidchallenge.data.local.dao

import androidx.room.*
import br.com.hurbandroidchallenge.data.local.model.*

@Dao
interface StarWarsBookDao {

    @Insert
    suspend fun insertNewCharacters(characters: List<PeopleEntity>)

    @Update(entity = PeopleEntity::class)
    suspend fun updateCharacter(characters: UpdateEntity)

    @Insert
    suspend fun insertNewFilms(films:  List<FilmEntity>)

    @Update(entity = FilmEntity::class)
    suspend fun updateFilm(characters: UpdateEntity)

    @Insert
    suspend fun insertNewPlanets(planets:  List<PlanetEntity>)

    @Update(entity = PlanetEntity::class)
    suspend fun updatePlanet(characters: UpdateEntity)

    @Query("SELECT * FROM people")
    suspend fun getCharacters(): List<PeopleEntity>

    @Query("SELECT * FROM people WHERE id = :id")
    suspend fun getCharacterById(id: Int): PeopleEntity

    @Query("DELETE FROM people")
    suspend fun clearCharacters()

    @Query("SELECT COUNT(1) FROM people WHERE id = :id")
    suspend fun containsCharacter(id: Int): Int

    @Query("SELECT * FROM films")
    suspend fun getFilms(): List<FilmEntity>

    @Query("SELECT * FROM films WHERE id = :id")
    suspend fun getFilmById(id: Int): FilmEntity

    @Query("DELETE FROM films")
    suspend fun clearFilms()

    @Query("SELECT COUNT(1) FROM films WHERE id = :id")
    suspend fun containsFilm(id: Int): Int

    @Query("SELECT * FROM planets")
    suspend fun getPlanets(): List<PlanetEntity>

    @Query("SELECT * FROM planets WHERE id = :id")
    suspend fun getPlanetById(id: Int): PlanetEntity

    @Query("SELECT COUNT(1) FROM planets WHERE id = :id")
    suspend fun containsPlanets(id: Int): Int

    @Query("DELETE FROM planets")
    suspend fun clearPlanets()

}