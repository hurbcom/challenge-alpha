package com.example.challengealphaandroid.data.room

import androidx.room.*
import com.example.challengealphaandroid.model.Starship

@Dao
interface StarshipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(starship: Starship)

    @Query("SELECT * FROM starship order by name asc")
    suspend fun getAll(): List<Starship>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(starships: Collection<Starship>)

    @Query("DELETE FROM starship")
    suspend fun deleteAll()

    @Update
    suspend fun update(starship: Starship)
}