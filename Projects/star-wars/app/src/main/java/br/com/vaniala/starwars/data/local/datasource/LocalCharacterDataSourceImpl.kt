package br.com.vaniala.starwars.data.local.datasource

import br.com.vaniala.starwars.data.local.dao.CharacterDao
import br.com.vaniala.starwars.domain.model.People
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
class LocalCharacterDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao,
) : LocalDataSource.Characters {
    override suspend fun insertAll(characters: List<People>) =
        characterDao.insertAll(characters)

    override suspend fun characterByName(query: String): List<People> =
        characterDao.characterByName(query)

    override suspend fun updateIsFavorite(isFavorite: Boolean, name: String) =
        characterDao.updateIsFavorite(isFavorite, name)

    override suspend fun lastUpdated(): Long =
        characterDao.lastUpdated()

    override suspend fun getCount(): Int =
        characterDao.getCount()
}
