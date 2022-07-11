package com.edufelip.challengealpha.domain.usecases

import com.edufelip.challengealpha.data.local.room.models.Favorite
import com.edufelip.challengealpha.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesListUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    operator fun invoke(): Flow<List<Favorite>> {
        return repository.getFavoritesList()
    }
}