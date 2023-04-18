package br.com.vaniala.starwars.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.vaniala.starwars.domain.model.People

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(characters: List<People>)

    @Query("SELECT * FROM character WHERE name LIKE '%' || :query || '%'")
    suspend fun characterByName(query: String): List<People>

    @Query("SELECT * FROM character WHERE name = :query")
    fun getCharacterByName(query: String): People

    @Query("UPDATE character SET isFavorite_character = :isFavorite WHERE name = :name")
    suspend fun updateIsFavorite(isFavorite: Boolean, name: String)

    @Query("SELECT timestamp_character FROM character ORDER BY timestamp_character DESC LIMIT 1")
    fun lastUpdated(): Long

    @Query("SELECT COUNT(*) FROM character")
    suspend fun getCount(): Int

    @Query("SELECT * FROM character WHERE isFavorite_character = 1")
    suspend fun getFavorites(): List<People>
}
