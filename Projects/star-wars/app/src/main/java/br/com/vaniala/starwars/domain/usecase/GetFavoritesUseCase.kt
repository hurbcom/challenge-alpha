package br.com.vaniala.starwars.domain.usecase

import br.com.vaniala.starwars.core.State
import br.com.vaniala.starwars.domain.model.Favorite
import br.com.vaniala.starwars.domain.repository.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
class GetFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) {
    operator fun invoke(): Flow<State<Favorite>> = flow {
        val favorites = favoritesRepository.getFavorites()
        emit(State.Success(favorites))
    }.catch { e ->
        e.printStackTrace()
    }.flowOn(Dispatchers.IO)
}
