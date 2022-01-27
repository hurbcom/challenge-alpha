package com.br.natanbrito.challenge.data.datasource

import com.br.natanbrito.challenge.data.model.HotelNetworkResult
import com.br.natanbrito.challenge.factory.HotelResponseFactory
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class HotelDataSourceImplTest {

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    private val dataSourceImpl: HotelDataSourceImpl = mockk()

    @ExperimentalCoroutinesApi
    @Test
    fun `Hotels request return success`() {
        val success = HotelResponseFactory.createHotels()

        coEvery { dataSourceImpl.fetchHotels() } returns HotelNetworkResult.Success(success)

        dispatcher.runBlockingTest {
            val result = kotlin.runCatching {
                dataSourceImpl.fetchHotels()
            }

            assert(result.isSuccess)
        }
    }
}
