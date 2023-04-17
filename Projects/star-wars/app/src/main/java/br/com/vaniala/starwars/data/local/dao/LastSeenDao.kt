package br.com.vaniala.starwars.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.vaniala.starwars.domain.model.LastSeen

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
@Dao
interface LastSeenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lastSeen: LastSeen)

    @Query("SELECT * FROM last_seen")
    suspend fun getLastSeen(): List<LastSeen>
}
