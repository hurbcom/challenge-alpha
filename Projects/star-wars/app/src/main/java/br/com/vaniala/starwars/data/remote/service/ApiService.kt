package br.com.vaniala.starwars.data.remote.service

import br.com.vaniala.starwars.domain.model.ApiResponse
import br.com.vaniala.starwars.domain.model.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
interface ApiService {
    @GET("api")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("api/{category}/")
    suspend fun <T : Any> getCategory(
        @Path("category") category: String,
        @Path("page") page: Int,
    ): Response<ApiResponse<T>>
}
