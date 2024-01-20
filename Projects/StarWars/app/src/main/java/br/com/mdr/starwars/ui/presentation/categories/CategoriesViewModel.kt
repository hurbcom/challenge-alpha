package br.com.mdr.starwars.ui.presentation.categories

import br.com.mdr.starwars.domain.model.Category
import br.com.mdr.starwars.domain.model.PageState
import br.com.mdr.starwars.domain.usecase.CategoryUseCase
import br.com.mdr.starwars.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.single

class CategoriesViewModel(
    private val useCase: CategoryUseCase
) : BaseViewModel() {

    private val _categories = MutableStateFlow<PageState<List<Category>>>(PageState.Loading)
    val categories: StateFlow<PageState<List<Category>>>
        get() = _categories

    override suspend fun refresh() {
        getCategories()
    }

    suspend fun getCategories() {
        launch {
            _categories.emit(useCase.getCategories().single())
        }
    }
}
