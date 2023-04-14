package br.com.vaniala.starwars.data.repository

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
    private val dataSource: RemoteDataSource,
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): ApiResponse<People> =
        dataSource.getCharacters(page)
}
