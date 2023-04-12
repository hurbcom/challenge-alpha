package br.com.vaniala.starwars.data.remote

import br.com.vaniala.starwars.domain.model.CategoryResponse
import retrofit2.Response

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
interface RemoteDataSource {
    suspend fun getCategories(): Response<CategoryResponse>
}
