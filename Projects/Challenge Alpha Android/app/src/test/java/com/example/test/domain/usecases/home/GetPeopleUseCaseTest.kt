package com.example.test.domain.usecases.home

import com.example.core.base.data.BaseResult
import com.example.test.MainDispatcherRule
import com.example.test.domain.repositories.home.PeopleRepository
import com.example.test.domain.usecases.ListGetParams
import com.example.test.mocks.UseCaseMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.*

class GetPeopleUseCaseTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var peopleRepository: PeopleRepository

    private lateinit var useCase: GetPeopleUseCase

    @Before
    fun setUp() {
        peopleRepository = mockk()
        useCase = GetPeopleUseCase(peopleRepository)
    }

    @Test
    fun `when performAction is called then repository getPeople method should be called`() {

        val people = UseCaseMocks.people
        val successResponse = flowOf(BaseResult.Success(people, hashMapOf()))

        coEvery { peopleRepository.getPeople(anyInt(), anyString()) } coAnswers { successResponse }

        useCase.performAction(ListGetParams(anyInt(), anyString()))

        coVerify { peopleRepository.getPeople(anyInt(), anyString()) }
    }
}