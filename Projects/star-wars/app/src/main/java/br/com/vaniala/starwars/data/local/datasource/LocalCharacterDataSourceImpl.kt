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

    override suspend fun charactersByName(query: String): List<People> =
        characterDao.charactersByName(query)

    override suspend fun getCharacterByName(query: String): People =
        characterDao.getCharacterByName(query)

    override suspend fun updateIsFavorite(isFavorite: Boolean, name: String) =
        characterDao.updateIsFavorite(isFavorite, name)

    override suspend fun getFavorites(): List<People> =
        characterDao.getFavorites()

    override suspend fun isUpdate(): Boolean =
        characterDao.isUpdate()
}
