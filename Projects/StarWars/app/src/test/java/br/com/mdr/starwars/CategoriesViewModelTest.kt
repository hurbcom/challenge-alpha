package br.com.mdr.starwars

import br.com.mdr.starwars.base.BaseViewModelTest
import br.com.mdr.starwars.di.viewModelModule
import br.com.mdr.starwars.domain.model.Category
import br.com.mdr.starwars.domain.model.PageState
import br.com.mdr.starwars.domain.repository.CategoryRepository
import br.com.mdr.starwars.domain.usecase.CategoryUseCase
import br.com.mdr.starwars.mocks.categories
import br.com.mdr.starwars.mocks.emit
import br.com.mdr.starwars.mocks.emptyCategories
import br.com.mdr.starwars.mocks.errorCategory
import br.com.mdr.starwars.mocks.mockedException
import br.com.mdr.starwars.ui.presentation.categories.CategoriesViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.inject
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class CategoriesViewModelTest: BaseViewModelTest() {
    private val viewModel: CategoriesViewModel by inject()
    private val useCase: CategoryUseCase by inject()
    private val repository: CategoryRepository by inject()

    @Before
    fun setUp() {
        loadKoinModules(
            module {
                single { mockk<CategoryRepository>() }
                single { mockk<CategoryUseCase>() }
            } +
            viewModelModule
        )
    }

    @Test
    fun givenSuccess_whenLoadingCategories_thenCheckSuccessResult() {
        //given
        var expectedState: PageState<List<Category>> = PageState.Loading

        assertEquals(expectedState, viewModel.categories.value)

        expectedState = categories

        coEvery { useCase.getCategories() } returns emit(categories)

        //when
        viewModel.getCategories()

        //then
        assertEquals(expectedState, viewModel.categories.value)
    }

    @Test
    fun givenSuccess_whenLoadingCategories_thenEmptyResult() {
        //given
        var expectedState: PageState<List<Category>> = PageState.Loading

        assertEquals(expectedState, viewModel.categories.value)

        expectedState = emptyCategories

        coEvery { useCase.getCategories() } returns emit(emptyCategories)

        //when
        viewModel.getCategories()

        //then
        assertEquals(expectedState, viewModel.categories.value)
    }

    @Test
    fun givenError_whenLoadingCategories_thenCheckError() {
        //given
        var expectedState: PageState<List<Category>> = PageState.Loading

        assertEquals(expectedState, viewModel.categories.value)

        expectedState = errorCategory

        coEvery { repository.getCategories() } throws mockedException
        coEvery { useCase.getCategories() } returns emit(errorCategory)

        //when
        viewModel.getCategories()

        //then
        assertEquals(expectedState, viewModel.categories.value)
    }

}