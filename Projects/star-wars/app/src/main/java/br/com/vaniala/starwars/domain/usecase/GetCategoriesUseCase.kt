package br.com.vaniala.starwars.domain.usecase

import br.com.vaniala.starwars.core.Result
import br.com.vaniala.starwars.core.StatusConnectivity
import br.com.vaniala.starwars.data.mapper.toCategoryList
import br.com.vaniala.starwars.domain.model.Category
import br.com.vaniala.starwars.domain.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository,
    private val saveCategoriesInBD: SaveCategoriesInBD,
    private val getCategoriesFromBD: GetCategoriesFromBD,
    private val statusConnectivity: StatusConnectivity,
) {
    operator fun invoke(): Flow<Result<List<Category>>> = flow {
        emit(Result.Loading)
        val isDataUpdate = repository.isUpdate()
        val isNotConnected = !statusConnectivity.isConnected()

        if (isDataUpdate || isNotConnected) {
            val categories = getCategoriesFromBD()
            emit(Result.Success(categories))
        } else {
            val response = repository.getCategories()

            if (response.isSuccessful) {
                val categoryResult = response.body()
                if (categoryResult != null) {
                    val categoriesList = categoryResult.toCategoryList()
                    saveCategoriesInBD(categoriesList)
                    emit(Result.Success(categoriesList))
                }
            } else {
                emit(Result.Failure(Exception("Error getting categories")))
            }
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Result.Failure(Exception(e)))
    }.flowOn(Dispatchers.IO)
}
