package br.com.mdr.starwars.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.mdr.starwars.domain.model.LastSeen

@Dao
interface LastSeenDao {
    @Query("select * from last_seen_table")
    suspend fun getLastSeen(): List<LastSeen>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLastSeen(lastSeen: LastSeen)
}