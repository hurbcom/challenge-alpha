package com.example.test.data.repositories.home

import com.example.core.base.data.BaseResult
import com.example.test.MainDispatcherRule
import com.example.test.data.datasources.network.StarWarsApi
import com.example.test.data.mappers.DataMapper.map
import com.example.test.domain.models.Person
import com.example.test.domain.repositories.home.PeopleRepository
import com.example.test.mocks.RepositoryMocks
import com.example.test.utils.Constants
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString

class PeopleRepositoryTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: PeopleRepository

    private lateinit var apiService: StarWarsApi

    @Before
    fun setUp() {
        apiService = mockk()
        repository = PeopleRepositoryImpl(apiService)
    }

    @Test
    fun `when useCase calls performAction then repository getPeople should be called and success response returned`() =
        runBlocking {
            val people = RepositoryMocks.peopleResponse
            val expected = BaseResult.Success(
                people.results?.map { it.map() },
                hashMapOf(Constants.NEXT_PAGE_KEY to people.next)
            )
            coEvery { apiService.getPeople(anyInt(), anyString()) } coAnswers { people }

            val result = repository.getPeople(anyInt(), anyString())

            Assert.assertEquals(expected, result.first())

            coVerify { apiService.getPeople(anyInt(), anyString()) }
        }

}