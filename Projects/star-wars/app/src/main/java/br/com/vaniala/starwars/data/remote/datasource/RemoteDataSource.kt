package br.com.vaniala.starwars.data.remote.datasource

import br.com.vaniala.starwars.domain.model.ApiResponse
import br.com.vaniala.starwars.domain.model.CategoryResponse
import br.com.vaniala.starwars.domain.model.Film
import br.com.vaniala.starwars.domain.model.People
import retrofit2.Response

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
interface RemoteDataSource {
    suspend fun getCategories(): Response<CategoryResponse>
    suspend fun getFilms(page: Int): ApiResponse<Film>
    suspend fun getCharacters(page: Int): ApiResponse<People>
}
