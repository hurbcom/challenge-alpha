package com.br.natanbrito.challenge.data.repository

import com.br.natanbrito.challenge.factory.HotelResponseFactory
import com.br.natanbrito.challenge.data.datasource.HotelDataSource
import com.br.natanbrito.challenge.data.model.HotelNetworkResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class HotelRepositoryImplTest {

    private val dataSource: HotelDataSource = mockk()
    private val repository = HotelRepositoryImpl(dataSource)
    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    @Test
    fun `fetchHotels should return true`() {
        val expectedResponse = HotelResponseFactory.createHotels()

        coEvery { dataSource.fetchHotels() } returns HotelNetworkResult.Success(expectedResponse)

        dispatcher.runBlockingTest {

            val response = repository.getHotels()

            coVerify(exactly = 1) {
                dataSource.fetchHotels()
            }

            assert(expectedResponse.results == response)

            confirmVerified(dataSource)
        }
    }
}