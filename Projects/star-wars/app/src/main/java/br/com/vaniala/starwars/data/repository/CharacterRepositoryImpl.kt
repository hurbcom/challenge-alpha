package br.com.vaniala.starwars.data.repository

import br.com.vaniala.starwars.data.local.datasource.LocalDataSource
import br.com.vaniala.starwars.data.remote.datasource.RemoteDataSource
import br.com.vaniala.starwars.domain.model.ApiResponse
import br.com.vaniala.starwars.domain.model.People
import br.com.vaniala.starwars.domain.repository.CharacterRepository
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
class CharacterRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource.Characters,
    private val dataSource: RemoteDataSource,
) : CharacterRepository {
    override suspend fun insertAll(characters: List<People>) =
        localDataSource.insertAll(characters)

    override suspend fun getCharacters(page: Int): ApiResponse<People> =
        dataSource.getCharacters(page)

    override suspend fun charactersByName(query: String): List<People> =
        localDataSource.charactersByName(query)

    override suspend fun getCharacterByName(query: String): People =
        localDataSource.getCharacterByName(query)

    override suspend fun updateIsFavorite(isFavorite: Boolean, name: String) =
        localDataSource.updateIsFavorite(isFavorite, name)

    override suspend fun isUpdate(): Boolean =
        localDataSource.isUpdate()
}
