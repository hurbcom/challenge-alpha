package br.com.mdr.starwars.data.repository

import br.com.mdr.starwars.data.remote.StarWarsApi
import br.com.mdr.starwars.data.remote.model.CategoryResponse
import br.com.mdr.starwars.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryRepositoryImpl(
    private val api: StarWarsApi
): CategoryRepository {

    override suspend fun getCategories(): Flow<CategoryResponse> =
        flow {
            emit(api.getCategories())
        }
}
