package com.example.hurb_challenge.repository

import com.example.hurb_challenge.Mocks
import com.example.hurb_challenge.app.data.ApiService
import com.example.hurb_challenge.app.data.repository.FilmsRepositoryImpl
import com.example.hurb_challenge.app.domain.repository.FilmsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class FilmsRepositoryTest {

    private lateinit var repository: FilmsRepository
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        apiService = mockk<ApiService>()
        repository = FilmsRepositoryImpl(apiService)
    }

    @Test
    fun `should return a flow of api response film dto when service call returns success`() = runTest {
        val expectedResponse = Mocks.apiResponseFilmDto
        coEvery { apiService.getFilms(1) } returns expectedResponse

        val result = repository.getFilms(1)
        result.collect {
            assertEquals(it.count, expectedResponse.count)
            assertEquals(it.next, expectedResponse.next)
            assertEquals(it.previous, expectedResponse.previous)
            assertEquals(it.results, expectedResponse.results)
            assertEquals(it, expectedResponse)
        }

        coVerify{ apiService.getFilms(1) }
    }

    @Test
    fun `should return a flow with exception when service call returns an error`() = runTest {
        val expectedResponse = mockk<HttpException>()
        coEvery { apiService.getFilms(1) } throws expectedResponse

        val result = repository.getFilms(1)

        result.collect {
            assertEquals(it, expectedResponse)
        }

        coVerify { apiService.getFilms(1) }
    }
}