package br.com.vaniala.starwars.data.remote.service

import br.com.vaniala.starwars.domain.model.ApiResponse
import br.com.vaniala.starwars.domain.model.CategoryResponse
import br.com.vaniala.starwars.domain.model.Films
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
interface ApiService {
    @GET("api")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("api/films/")
    suspend fun getFilms(
        @Query("page") page: Int,
    ): ApiResponse<Films>
}
