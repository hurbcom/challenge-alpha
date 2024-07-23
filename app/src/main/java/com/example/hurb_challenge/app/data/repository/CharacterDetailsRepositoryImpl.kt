package com.example.hurb_challenge.app.data.repository

import com.example.hurb_challenge.app.core.extractNumbers
import com.example.hurb_challenge.app.data.ApiService
import com.example.hurb_challenge.app.data.Result
import com.example.hurb_challenge.app.data.asResult
import com.example.hurb_challenge.app.domain.model.Starship
import com.example.hurb_challenge.app.domain.model.Vehicle
import com.example.hurb_challenge.app.domain.repository.CharacterDetailsRepository
import com.example.hurb_challenge.app.domain.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CharacterDetailsRepository {

    override suspend fun getVehicles(urls: List<String>): Flow<Result<List<Vehicle>>> {
        return flow {
            val vehicles = mutableListOf<Vehicle>()
            urls.forEach { url ->
                val dto = apiService.getVehicle(url)
                val id = dto.url.extractNumbers()
                vehicles.add(dto.toDomain(id))
            }
            emit(vehicles)
        }.asResult()
    }

    override suspend fun getStarships(urls: List<String>): Flow<Result<List<Starship>>> {
        return flow {
            val starships = mutableListOf<Starship>()
            urls.forEach { url ->
                val dto = apiService.getStarship(url)
                val id = dto.url.extractNumbers()
                starships.add(dto.toDomain(id))
            }
            emit(starships)
        }.asResult()
    }
}