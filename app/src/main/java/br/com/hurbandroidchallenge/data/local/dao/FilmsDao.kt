package br.com.hurbandroidchallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.model.UpdateEntity

@Dao
interface FilmsDao {

    @Insert
    suspend fun insertNewFilms(films:  List<FilmEntity>)

    @Update(entity = FilmEntity::class)
    suspend fun updateFilm(characters: UpdateEntity)

    @Query("SELECT * FROM films")
    suspend fun getFilms(): List<FilmEntity>

    @Query("SELECT * FROM films WHERE id = :id")
    suspend fun getFilmById(id: Int): FilmEntity

    @Query("DELETE FROM films")
    suspend fun clearFilms()

    @Query("SELECT COUNT(1) FROM films WHERE id = :id")
    suspend fun containsFilm(id: Int): Int

}