package com.example.challengealphaandroid.data.room

import androidx.room.*
import com.example.challengealphaandroid.model.Planet

@Dao
interface PlanetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(planet: Planet)

    @Query("SELECT * FROM planet order by name asc")
    suspend fun getAll(): List<Planet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(planets: Collection<Planet>)

    @Query("DELETE FROM planet")
    suspend fun deleteAll()

    @Update
    suspend fun update(planet: Planet)
}