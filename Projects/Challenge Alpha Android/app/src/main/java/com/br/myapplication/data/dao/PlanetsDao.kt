package com.br.myapplication.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.br.myapplication.data.model.Planet

@Dao
interface PlanetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanets(planets: Planet)

    @Query("SELECT * FROM planets_table ORDER BY id ASC")
    fun getAllPlanets(): PagingSource<Int, Planet>

    @Query("SELECT * FROM planets_table ORDER BY id ASC LIMIT :pageSize OFFSET :offset")
    fun getAllPlanetsPaging(offset: Int, pageSize: Int): List<Planet>

    @Query("SELECT * FROM planets_table WHERE name LIKE '%' || :filter || '%'")
    fun getFilteredPlanetsPagingSource(filter: String?): PagingSource<Int, Planet>

    @Query("SELECT * FROM planets_table WHERE isFavorite = 1" )
    suspend fun getFavoritesPlanets(): List<Planet>

    @Update
    suspend fun updatePlanet(planet: Planet)
}