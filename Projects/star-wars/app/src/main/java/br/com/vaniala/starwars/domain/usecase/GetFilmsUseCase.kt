package br.com.vaniala.starwars.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.vaniala.starwars.data.remote.FilmPagingSource
import br.com.vaniala.starwars.data.remote.service.ApiService
import br.com.vaniala.starwars.domain.model.Films
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */
private const val NETWORK_PAGE_SIZE = 10

class GetFilmsUseCase @Inject constructor(
    private val service: ApiService,
) {

    operator fun invoke(): Flow<PagingData<Films>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { FilmPagingSource(service) },
        ).flow.flowOn(Dispatchers.IO)
    }
}
