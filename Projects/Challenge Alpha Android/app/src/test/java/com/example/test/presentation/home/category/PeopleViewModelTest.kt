package com.example.test.presentation.home.category

import com.example.core.base.data.BaseResult
import com.example.core.base.presentation.BaseViewData
import com.example.test.MainDispatcherRule
import com.example.test.domain.usecases.home.GetPeopleUseCase
import com.example.test.mocks.ViewModelMocks
import com.example.test.presentation.models.CategoryItemDetailsViewData
import com.example.test.presentation.models.CategoryType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PeopleViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var useCase: GetPeopleUseCase

    private lateinit var viewModel: PeopleViewModel

    @Before
    fun setUp() {
        useCase = mockk()
        viewModel = PeopleViewModel(useCase)
    }

    @Test
    fun `when view calls get list data then use case performAction should be called and success event emitted`() =
        runBlocking {
            val people = ViewModelMocks.people
            val loadingState = BaseResult.Loading(true)
            val successState = flowOf(BaseResult.Success(people, hashMapOf()))

            coEvery { useCase.performAction(any()) } coAnswers { successState }

            val results = arrayListOf<BaseResult<List<BaseViewData>>>()
            val job = launch { viewModel.state.toList(results) }

            viewModel.getListData()

            coVerify { useCase.performAction(any()) }
            assertEquals(loadingState, results.first())
            assertEquals(successState.first(), results[1])

            job.cancel()
        }

    @Test
    fun `when view calls get list data then use case performAction should be called and error event emitted`() =
        runBlocking {
            val loadingState = BaseResult.Loading(true)
            val errorState = flowOf(BaseResult.Error(Exception()))

            coEvery { useCase.performAction(any()) } coAnswers { errorState }

            val results = arrayListOf<BaseResult<List<BaseViewData>>>()
            val job = launch { viewModel.state.toList(results) }

            viewModel.getListData()

            coVerify { useCase.performAction(any()) }
            assertEquals(loadingState, results.first())
            assertEquals(errorState.first(), results[1])

            job.cancel()
        }
}