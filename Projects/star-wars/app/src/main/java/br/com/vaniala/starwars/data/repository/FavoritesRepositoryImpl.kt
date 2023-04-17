package br.com.vaniala.starwars.data.repository

import br.com.vaniala.starwars.data.local.datasource.LocalDataSource
import br.com.vaniala.starwars.domain.model.Favorite
import br.com.vaniala.starwars.domain.repository.FavoritesRepository
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
class FavoritesRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource.Favorites,
) : FavoritesRepository {
    override suspend fun getFavorites(): Favorite =
        localDataSource.getFavorites()
}
