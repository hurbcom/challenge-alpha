package com.example.hurb_challenge.app.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.example.hurb_challenge.app.data.pagingsource.CharactersPagingSource
import com.example.hurb_challenge.app.domain.repository.CharactersRepository
import com.example.hurb_challenge.app.domain.toCardItem
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) : HomeUseCase.GetCharactersUseCase {
    override fun call() =
        Pager(
            config = PagingConfig(HomeUseCase.PAGE_SIZE, HomeUseCase.PREFETCH_DISTANCE),
            pagingSourceFactory = {
                CharactersPagingSource(repository)
            }
        ).flow.map { pagingData ->
            pagingData.map { character -> character.toCardItem() }
        }
}
