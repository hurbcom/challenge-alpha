package br.com.vaniala.starwars.domain.repository

import br.com.vaniala.starwars.domain.model.CategoryResponse
import retrofit2.Response

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
interface CategoryRepository {

    suspend fun getCategories(): Response<CategoryResponse>
}
