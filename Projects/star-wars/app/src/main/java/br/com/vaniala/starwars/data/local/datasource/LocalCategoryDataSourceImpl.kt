package br.com.vaniala.starwars.data.local.datasource

import br.com.vaniala.starwars.data.local.dao.CategoryDao
import br.com.vaniala.starwars.domain.model.Category
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
class LocalCategoryDataSourceImpl @Inject constructor(
    private val categoryDao: CategoryDao,
) : LocalDataSource.Categories {
    override suspend fun insertAll(categories: List<Category>) =
        categoryDao.insertAll(categories)

    override suspend fun getAll(): List<Category> =
        categoryDao.getAll()

    override suspend fun isUpdate(): Boolean =
        categoryDao.isUpdate()
}
