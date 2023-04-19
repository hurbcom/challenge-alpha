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
    suspend fun charactersByName(query: String): List<People>

    @Query("SELECT * FROM character WHERE name = :query")
    suspend fun getCharacterByName(query: String): People

    @Query("UPDATE character SET isFavorite_character = :isFavorite WHERE name = :name")
    suspend fun updateIsFavorite(isFavorite: Boolean, name: String)

    @Query("SELECT * FROM character WHERE isFavorite_character = 1")
    suspend fun getFavorites(): List<People>

    @Query("SELECT EXISTS(SELECT 1 FROM character WHERE is_update_character = 1 LIMIT 1)")
    suspend fun isUpdate(): Boolean
}
