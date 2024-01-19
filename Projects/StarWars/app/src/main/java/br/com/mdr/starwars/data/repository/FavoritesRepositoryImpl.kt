package br.com.mdr.starwars.data.repository

import br.com.mdr.starwars.domain.model.Favorite
import br.com.mdr.starwars.domain.repository.FavoritesRepository
import br.com.mdr.starwars.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow

class FavoritesRepositoryImpl(
    private val localDataSource: LocalDataSource
): FavoritesRepository {

    override suspend fun getFavorites(): Flow<Favorite> {
        return localDataSource.getFavorites()
    }
}