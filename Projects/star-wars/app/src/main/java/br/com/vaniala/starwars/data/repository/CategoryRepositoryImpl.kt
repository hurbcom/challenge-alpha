package br.com.vaniala.starwars.data.repository

import br.com.vaniala.starwars.data.local.datasource.LocalDataSource
import br.com.vaniala.starwars.data.remote.datasource.RemoteDataSource
import br.com.vaniala.starwars.domain.model.Category
import br.com.vaniala.starwars.domain.model.CategoryResponse
import br.com.vaniala.starwars.domain.repository.CategoryRepository
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
class CategoryRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource.Categories,
    private val remoteDataSource: RemoteDataSource,
) : CategoryRepository {
    override suspend fun getCategories(): Response<CategoryResponse> =
        remoteDataSource.getCategories()

    override suspend fun insertAll(categories: List<Category>) =
        localDataSource.insertAll(categories)

    override fun getAll(): List<Category> =
        localDataSource.getAll()
}
