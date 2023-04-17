package br.com.vaniala.starwars.data.local.datasource

import br.com.vaniala.starwars.data.local.dao.LastSeenDao
import br.com.vaniala.starwars.domain.model.LastSeen
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
class LocalLastSeenDataSourceImpl @Inject constructor(
    private val lastSeenDao: LastSeenDao,
) : LocalDataSource.LastSeenI {

    override suspend fun getLastSeen(): List<LastSeen> =
        lastSeenDao.getLastSeen()

    override suspend fun insert(lastSeen: LastSeen) =
        lastSeenDao.insert(lastSeen)
}
