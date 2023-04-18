package br.com.vaniala.starwars.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.vaniala.starwars.core.StatusConnectivity
import br.com.vaniala.starwars.data.local.database.StarWarsDatabase
import br.com.vaniala.starwars.data.remote.paging.FilmPagingSource
import br.com.vaniala.starwars.domain.model.Film
import br.com.vaniala.starwars.domain.repository.FilmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
private const val PAGE_SIZE = 10

class GetFilmsBDAndRemoteUseCase @Inject constructor(
    private val repository: FilmRepository,
    private val starWarsDatabase: StarWarsDatabase,
    private val statusConnectivity: StatusConnectivity,
) {
    operator fun invoke(query: String = ""): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true,
            ),
            pagingSourceFactory = {
                FilmPagingSource(
                    query,
                    repository,
                    starWarsDatabase,
                    statusConnectivity,
                )
            },
        ).flow.flowOn(Dispatchers.IO)
    }
}
