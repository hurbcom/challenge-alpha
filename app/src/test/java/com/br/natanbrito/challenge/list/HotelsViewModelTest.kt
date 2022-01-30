package com.br.natanbrito.challenge.list

import com.br.natanbrito.challenge.alpha.hotel.list.HotelsViewModel
import com.br.natanbrito.challenge.data.model.HotelNetworkResult
import com.br.natanbrito.challenge.domain.usecases.GetHotelsUseCase
import com.br.natanbrito.challenge.factory.HotelResponseFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class HotelsViewModelTest {

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: HotelsViewModel
    private val hotelsUseCase: GetHotelsUseCase = mockk()

    @ExperimentalCoroutinesApi
    @Before
    fun before() {
        viewModel = HotelsViewModel(hotelsUseCase)
        Dispatchers.setMain(dispatcher)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Assert that fetchHotels is successful`() = dispatcher.runBlockingTest {
        coEvery {
            hotelsUseCase.invoke()
        } returns HotelNetworkResult.Success(HotelResponseFactory.createHotels())

        val result = runCatching {
            viewModel.prepareDataRequest(true)
        }

        coVerify { hotelsUseCase.invoke() }
        assert(result.isSuccess)
        assert(result.getOrNull() != null)
    }
}
