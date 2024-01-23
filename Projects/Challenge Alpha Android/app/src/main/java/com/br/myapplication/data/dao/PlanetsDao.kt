package com.br.myapplication.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.br.myapplication.data.model.Planet

@Dao
interface PlanetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanets(planets: Planet)

    @Query("SELECT * FROM planets_tables ORDER BY id ASC")
    fun getAllPlanets(): PagingSource<Int, Planet>
}