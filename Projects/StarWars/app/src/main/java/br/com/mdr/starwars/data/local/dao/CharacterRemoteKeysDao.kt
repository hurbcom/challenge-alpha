package br.com.mdr.starwars.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.mdr.starwars.domain.model.CharacterRemoteKeys

@Dao
interface CharacterRemoteKeysDao {
    @Query("SELECT * FROM character_remote_keys_table WHERE name = :name")
    suspend fun getRemoteKeys(name: String): CharacterRemoteKeys?

    @Query("SELECT * FROM character_remote_keys_table ORDER BY lastUpdated ASC LIMIT 1")
    suspend fun getRemoteKey(): CharacterRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKeys: List<CharacterRemoteKeys>)

    @Query("DELETE FROM character_remote_keys_table")
    suspend fun deleteRemoteKeys()
}
