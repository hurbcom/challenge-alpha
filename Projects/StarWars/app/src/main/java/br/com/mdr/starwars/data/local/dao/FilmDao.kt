package br.com.mdr.starwars.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.mdr.starwars.domain.model.Film

@Dao
interface FilmDao {
    // Using PagingSource, allowing to paginate our db queries
    // Room supports Paging3 by default, so you don't need to create a custom PagingSource
    @Query("select * from film_table ORDER BY id ASC")
    fun getFilms(): PagingSource<Int, Film>

    @Query("select * from film_table WHERE id=:filmId")
    fun getSelectedFilm(filmId: Int): Film

    @Query("SELECT * FROM film_table WHERE title LIKE '%' || :query || '%'")
    fun getFilmsByTitle(query: String): PagingSource<Int, Film>

    @Query("UPDATE film_table SET isFilmFavorite = :isFavorite WHERE id = :id")
    suspend fun updateIsFavorite(isFavorite: Boolean, id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(films: List<Film>)

    @Query("DELETE FROM film_table")
    suspend fun deleteAllFilms()

    @Query("SELECT * FROM film_table WHERE isFilmFavorite = 1")
    suspend fun getFavoriteFilms(): List<Film>
}
