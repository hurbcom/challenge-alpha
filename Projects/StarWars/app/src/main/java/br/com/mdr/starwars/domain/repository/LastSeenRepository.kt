package br.com.mdr.starwars.domain.repository

import br.com.mdr.starwars.domain.model.LastSeen
import kotlinx.coroutines.flow.Flow

interface LastSeenRepository {
    suspend fun getLastSeen(): Flow<List<LastSeen>>
}
