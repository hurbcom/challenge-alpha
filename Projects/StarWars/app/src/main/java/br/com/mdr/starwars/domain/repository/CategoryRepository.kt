package br.com.mdr.starwars.domain.repository

import br.com.mdr.starwars.data.remote.model.CategoryResponse
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategories(): Flow<CategoryResponse>
}
