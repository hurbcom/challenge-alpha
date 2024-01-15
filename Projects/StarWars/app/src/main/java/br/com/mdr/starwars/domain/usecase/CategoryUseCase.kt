package br.com.mdr.starwars.domain.usecase

import br.com.mdr.starwars.data.remote.model.CategoryResponse
import br.com.mdr.starwars.domain.model.Category
import br.com.mdr.starwars.domain.model.PageState
import br.com.mdr.starwars.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart

class CategoryUseCase(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke() = flow {
        repository
            .getCategories()
            .onEmpty {
                emit(PageState.Empty)
            }
            .catch { error ->
                emit(PageState.Error(error))
            }
            .collect {
                emit(PageState.Success(it.toCategories()))
            }
    }

    private fun CategoryResponse.toCategories(): List<Category> {
        return listOf(
            Category(name = "films", url = this.films),
            Category(name = "people", url = this.people),
            Category(name = "planets", url = this.planets),
            Category(name = "species", url = this.species),
            Category(name = "starships", url = this.starships),
            Category(name = "vehicles", url = this.vehicles),
        )
    }
}