package br.com.vaniala.starwars.data.remote

import br.com.vaniala.starwars.data.remote.service.ApiService
import br.com.vaniala.starwars.domain.model.CategoryResponse
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
}
