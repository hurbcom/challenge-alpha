package br.com.vaniala.starwars.data.repository

import br.com.vaniala.starwars.data.local.datasource.LocalDataSource
import br.com.vaniala.starwars.domain.model.LastSeen
import br.com.vaniala.starwars.domain.repository.LastSeenRepository
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
class LastSeenRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource.LastSeenI,
) : LastSeenRepository {
    override suspend fun getLastSeen(): List<LastSeen> =
        localDataSource.getLastSeen()

    override suspend fun insert(lastSeen: LastSeen) =
        localDataSource.insert(lastSeen)
}
