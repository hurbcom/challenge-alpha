package br.com.vaniala.starwars.domain.usecase

import br.com.vaniala.starwars.data.mapper.toCategoryList
import br.com.vaniala.starwars.data.remote.service.Result
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
    private val categoryRepository: CategoryRepository,
) {

    operator fun invoke(): Flow<Result<List<Category>>> = flow {
        emit(Result.Loading)
        val response = categoryRepository.getCategories()

        if (response.isSuccessful) {
            val categoryResult = response.body()
            if (categoryResult != null) {
                val categoriesList = categoryResult.toCategoryList()
                emit(Result.Success(categoriesList))
            }
        } else {
            emit(Result.Failure(Exception("Error getting categories")))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Result.Failure(Exception(e)))
    }.flowOn(Dispatchers.IO)
}
