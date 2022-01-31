package com.br.natanbrito.challenge.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.br.natanbrito.challenge.alpha.hotel.list.HotelsViewModel
import com.br.natanbrito.challenge.data.model.HotelNetworkResult
import com.br.natanbrito.challenge.domain.usecases.GetHotelsUseCase
import com.br.natanbrito.challenge.factory.HotelResponseFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HotelsViewModelTest {

    @get:Rule
    var executorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: HotelsViewModel

    @MockK
    private lateinit var hotelsUseCase: GetHotelsUseCase

    @ExperimentalCoroutinesApi
    @Before
    fun before() {
        MockKAnnotations.init(this)
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
