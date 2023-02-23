package com.example.test.presentation.home.people

import com.example.core.base.data.BaseResult
import com.example.test.MainDispatcherRule
import com.example.test.domain.models.Person
import com.example.test.domain.usecases.home.GetPeopleUseCase
import com.example.test.presentation.home.category.PeopleViewModel
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
import org.junit.Rule
import org.junit.Test

class PeopleViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase = mockk<GetPeopleUseCase>()

    private val viewModel = PeopleViewModel(useCase)

    @Test
    fun `when view model calls get people method then it should emit expected states`() =
        runBlocking {
            val people = listOf(
                Person(
                    name = "Eduardo",
                    height = "177",
                    mass = "88",
                    gender = "male",
                    eyeColor = "black"
                )
            )
            val loadingState = BaseResult.Loading
            val successState = flowOf(BaseResult.Success(people, hashMapOf()))

            coEvery { useCase.performAction(any()) } coAnswers {
                successState
            }

            val results = arrayListOf<BaseResult<List<Person>>>()
            val job = launch { viewModel.state.toList(results) }

            viewModel.getPeople()

            coVerify { useCase.performAction(any()) }
            assertEquals(loadingState, results.first())
            assertEquals(successState.first(), results[1])

            job.cancel()
        }

}