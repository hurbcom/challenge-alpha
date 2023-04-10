package br.com.vaniala.starwars.data.remote

import br.com.vaniala.starwars.data.remote.service.CategoryService
import br.com.vaniala.starwars.domain.model.CategoryResult
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
class RemoteDataSourceImpl @Inject constructor(
    private val service: CategoryService,
) : RemoteDataSource {
    override suspend fun getCategories(): Response<CategoryResult> =
        service.getCategories()
}
