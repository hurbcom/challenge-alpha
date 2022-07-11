package com.edufelip.challengealpha.domain.usecases

import com.edufelip.challengealpha.data.local.room.models.Favorite
import com.edufelip.challengealpha.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    operator fun invoke(favorite: Favorite): Flow<Boolean> {
        return repository.deleteFavorite(favorite)
    }
}