package br.com.mdr.starwars.domain.usecase

import br.com.mdr.starwars.domain.model.Favorite
import br.com.mdr.starwars.domain.model.PageState
import br.com.mdr.starwars.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEmpty

class FavoritesUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    suspend fun getFavorites(): Flow<PageState<Favorite>> = flow {
        favoritesRepository
            .getFavorites()
            .onEmpty {
                emit(PageState.Empty)
            }
            .catch { error ->
                emit(PageState.Error(error))
            }
            .collect {
                emit(PageState.Success(it))
            }
    }
}
