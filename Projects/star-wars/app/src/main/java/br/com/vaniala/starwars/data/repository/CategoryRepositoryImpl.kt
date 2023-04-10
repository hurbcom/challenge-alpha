package br.com.vaniala.starwars.data.repository

import br.com.vaniala.starwars.data.remote.RemoteDataSource
import br.com.vaniala.starwars.domain.model.CategoryResult
import br.com.vaniala.starwars.domain.repository.CategoryRepository
import retrofit2.Response
import javax.inject.Inject
/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource,
) : CategoryRepository {
    override suspend fun getCategories(): Response<CategoryResult> =
        dataSource.getCategories()
}
