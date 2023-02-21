package com.example.test.presentation.home.people

import com.example.core.base.BaseResult
import com.example.test.MainDispatcherRule
import com.example.test.domain.models.Person
import com.example.test.domain.usecases.home.GetPeopleUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
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

    @Test
    fun `when view model call get people then should call getPeopleUseCase performAction`() =
        runBlocking {
            val viewModel = instantiateViewModel()
            val people = listOf(
                Person(
                    name = "Eduardo",
                    height = "177",
                    mass = "88",
                    gender = "male",
                    eyeColor = "black",
                    url = ""
                )
            )
            val successFlow = flowOf(BaseResult.Success(people, hashMapOf()))

            coEvery { useCase.performAction(any()) } coAnswers {
            delay(1000)
                successFlow
            }

            val results = arrayListOf<BaseResult<List<Person>>>()
            val job = launch { viewModel.state.toList(results) }

            viewModel.getPeople()

            assertEquals(BaseResult.Loading, results.first())
            coVerify { useCase.performAction(any()) }
            assertEquals(successFlow.first(), results[1])
            job.cancel()
        }

    private fun instantiateViewModel(): PeopleViewModel {
        return PeopleViewModel(useCase)
    }

}