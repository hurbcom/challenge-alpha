package br.com.vaniala.starwars.data.remote.service

import br.com.vaniala.starwars.domain.model.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET
/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
interface CategoryService {

    @GET(".")
    suspend fun getCategories(): Response<CategoryResponse>
}
