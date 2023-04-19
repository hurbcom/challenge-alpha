package br.com.vaniala.starwars.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.vaniala.starwars.domain.model.Film

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(films: List<Film>)

    @Query("SELECT * FROM film WHERE title LIKE '%' || :query || '%'")
    suspend fun filmsByTitle(query: String): List<Film>

    @Query("SELECT * FROM film WHERE title = :query")
    suspend fun getFilmByTitle(query: String): Film

    @Query("UPDATE film SET isFavorite_film = :isFavorite WHERE title = :title")
    suspend fun updateIsFavorite(isFavorite: Boolean, title: String)

    @Query("SELECT * FROM film WHERE isFavorite_film = 1")
    suspend fun getFavorites(): List<Film>

    @Query("SELECT EXISTS(SELECT 1 FROM film WHERE is_update_film = 1 LIMIT 1)")
    suspend fun isUpdate(): Boolean
}
