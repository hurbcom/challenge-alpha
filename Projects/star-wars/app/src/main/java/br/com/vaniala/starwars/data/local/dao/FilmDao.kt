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
    fun filmsByTitle(query: String): List<Film>

    @Query("UPDATE film SET isFavorite = :isFavorite WHERE title = :title")
    suspend fun updateIsFavorite(isFavorite: Boolean, title: String)

    @Query("SELECT timestamp FROM film ORDER BY timestamp DESC LIMIT 1")
    fun lastUpdated(): Long

    @Query("SELECT COUNT(*) FROM film")
    fun getCount(): Int
}
