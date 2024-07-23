package com.example.hurb_challenge.app.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.example.hurb_challenge.app.data.pagingsource.FilmsPagingSource
import com.example.hurb_challenge.app.domain.repository.FilmsRepository
import com.example.hurb_challenge.app.domain.toCardItem
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(
    private val repository: FilmsRepository
) : HomeUseCase.GetFilmsUseCase {

    override fun call() = Pager(
        config = PagingConfig(HomeUseCase.PAGE_SIZE, HomeUseCase.PREFETCH_DISTANCE),
        pagingSourceFactory = {
            FilmsPagingSource(repository)
        }
    ).flow.map { pagingData ->
        pagingData.map { film -> film.toCardItem() }
    }
}
