package br.com.vaniala.starwars.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.vaniala.starwars.core.StatusConnectivity
import br.com.vaniala.starwars.data.local.database.StarWarsDatabase
import br.com.vaniala.starwars.data.local.datasource.LocalDataSource
import br.com.vaniala.starwars.data.remote.paging.CharacterPagingSource
import br.com.vaniala.starwars.domain.model.People
import br.com.vaniala.starwars.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 */

private const val PAGE_SIZE = 10

class GetCharactersBDAndRemoteUseCase @Inject constructor(
    private val repository: CharacterRepository,
    private val starWarsDatabase: StarWarsDatabase,
    private val statusConnectivity: StatusConnectivity,
) {
    operator fun invoke(query: String = ""): Flow<PagingData<People>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true,
            ),
            pagingSourceFactory = {
                CharacterPagingSource(
                    query,
                    repository,
                    starWarsDatabase,
                    statusConnectivity,
                )
            },
        ).flow.flowOn(Dispatchers.IO)
    }
}
