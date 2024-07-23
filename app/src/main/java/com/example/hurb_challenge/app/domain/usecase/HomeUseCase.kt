package com.example.hurb_challenge.app.domain.usecase

import androidx.paging.PagingData
import com.example.hurb_challenge.app.domain.model.Character
import com.example.hurb_challenge.app.domain.model.Film
import com.example.hurb_challenge.app.domain.model.CardItem
import kotlinx.coroutines.flow.Flow

sealed interface HomeUseCase<T : Any> {
    companion object {
        const val PAGE_SIZE = 10
        const val PREFETCH_DISTANCE = 4
    }
    fun call(): Flow<PagingData<CardItem<T>>>
    interface GetFilmsUseCase : HomeUseCase<Film>
    interface GetCharactersUseCase : HomeUseCase<Character>
}
