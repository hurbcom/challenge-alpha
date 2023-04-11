package com.example.challengealphaandroid.data

import com.example.challengealphaandroid.data.room.StarshipDao
import com.example.challengealphaandroid.model.Starship
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStarshipRepositoryImpl @Inject constructor(private val starshipDao: StarshipDao) : LocalStarshipRepository {
    override suspend fun getStarshipCache(): List<Starship> {
        return starshipDao.getAll()
    }

    override suspend fun saveStarships(starships: List<Starship>) {
        starshipDao.insertAll(starships)
    }

    override suspend fun saveStarships(starship: Starship) {
        starshipDao.insert(starship)
    }

    suspend override fun updateStarship(starship: Starship) {
        starshipDao.update(starship)
    }

    suspend override fun deleteAll() {
        starshipDao.deleteAll()
    }
}