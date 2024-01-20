package br.com.mdr.starwars.data.repository

import br.com.mdr.starwars.domain.model.LastSeen
import br.com.mdr.starwars.domain.repository.LastSeenRepository
import br.com.mdr.starwars.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow

class LastSeenRepositoryImpl(
    private val localDataSource: LocalDataSource
): LastSeenRepository {

    override suspend fun getLastSeen(): Flow<List<LastSeen>> =
        localDataSource.getLastSeen()
}