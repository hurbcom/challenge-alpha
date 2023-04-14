package br.com.vaniala.starwars.data.remote.datasource

import br.com.vaniala.starwars.data.remote.service.ApiService
import br.com.vaniala.starwars.domain.model.ApiResponse
import br.com.vaniala.starwars.domain.model.CategoryResponse
import br.com.vaniala.starwars.domain.model.Film
import br.com.vaniala.starwars.domain.model.People
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
class RemoteDataSourceImpl @Inject constructor(
    private val service: ApiService,
) : RemoteDataSource {
    override suspend fun getCategories(): Response<CategoryResponse> =
        service.getCategories()

    override suspend fun getFilms(page: Int): ApiResponse<Film> =
        service.getFilms(page)

    override suspend fun getCharacters(page: Int): ApiResponse<People> =
        service.getPeople(page)
}
