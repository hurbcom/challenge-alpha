package br.com.mdr.starwars.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.mdr.starwars.domain.model.FilmRemoteKeys

@Dao
interface FilmRemoteKeysDao {
    @Query("SELECT * FROM film_remote_keys_table WHERE id = :id")
    suspend fun getRemoteKeys(id: Int): FilmRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKey(heroRemoteKeys: FilmRemoteKeys)

    @Query("DELETE FROM film_remote_keys_table")
    suspend fun deleteRemoteKeys()
}