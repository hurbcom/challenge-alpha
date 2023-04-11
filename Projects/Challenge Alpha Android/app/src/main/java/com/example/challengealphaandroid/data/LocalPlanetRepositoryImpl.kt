package com.example.challengealphaandroid.data

import com.example.challengealphaandroid.data.room.LocalPlanetRepository
import com.example.challengealphaandroid.data.room.PlanetDao
import com.example.challengealphaandroid.model.Planet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalPlanetRepositoryImpl @Inject constructor(private val planetDao: PlanetDao) :
    LocalPlanetRepository {
    override suspend fun getPlanetCache(): List<Planet> {
        return planetDao.getAll()
    }

    override suspend fun savePlanet(planets: List<Planet>) {
        planetDao.insertAll(planets)
    }

    override suspend fun savePlanet(planet: Planet) {
        planetDao.insert(planet)
    }

    suspend override fun updatePlanet(planet: Planet) {
        planetDao.update(planet)
    }

    suspend override fun deleteAll() {
        planetDao.deleteAll()
    }
}