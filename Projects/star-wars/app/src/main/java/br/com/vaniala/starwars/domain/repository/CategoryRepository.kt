package br.com.vaniala.starwars.domain.repository

import br.com.vaniala.starwars.domain.model.Category
import br.com.vaniala.starwars.domain.model.CategoryResponse
import retrofit2.Response

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
interface CategoryRepository {

    suspend fun getCategories(): Response<CategoryResponse>

    suspend fun insertAll(categories: List<Category>)

    suspend fun getAll(): List<Category>
    suspend fun isUpdate(): Boolean
}
