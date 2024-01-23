package com.br.myapplication.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.br.myapplication.data.model.Film

@Dao
interface FilmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(films: Film)

    @Query("SELECT * FROM films_table ORDER BY id ASC")
    fun getAllFilms(): PagingSource<Int, Film>

    @Query("SELECT * FROM films_table ORDER BY id ASC LIMIT :pageSize OFFSET :offset")
    fun getAllFilmsPaging(offset: Int, pageSize: Int): List<Film>
}