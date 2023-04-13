package br.com.vaniala.starwars.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.vaniala.starwars.data.remote.CharacterPagingSource
import br.com.vaniala.starwars.data.remote.service.ApiService
import br.com.vaniala.starwars.domain.model.People
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

private const val NETWORK_PAGE_SIZE = 10

class GetCharactersUseCase @Inject constructor(
    private val service: ApiService,
) {

    operator fun invoke(): Flow<PagingData<People>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { (CharacterPagingSource(service)) },
        ).flow.flowOn(Dispatchers.IO)
    }
}
