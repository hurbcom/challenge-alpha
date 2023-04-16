package br.com.hurbandroidchallenge.data.local.dao

import androidx.room.*
import br.com.hurbandroidchallenge.data.local.model.*

@Dao
interface PlanetsDao {

    @Insert
    suspend fun insertNewPlanets(planets:  List<PlanetEntity>)

    @Update(entity = PlanetEntity::class)
    suspend fun updatePlanet(characters: UpdateEntity)

    @Query("SELECT * FROM planets")
    suspend fun getPlanets(): List<PlanetEntity>

    @Query("SELECT * FROM planets WHERE id = :id")
    suspend fun getPlanetById(id: Int): PlanetEntity

    @Query("SELECT COUNT(1) FROM planets WHERE id = :id")
    suspend fun containsPlanets(id: Int): Int

    @Query("DELETE FROM planets")
    suspend fun clearPlanets()

}