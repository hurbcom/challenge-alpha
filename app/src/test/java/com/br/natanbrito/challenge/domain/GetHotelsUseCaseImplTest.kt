package com.br.natanbrito.challenge.domain

import com.br.natanbrito.challenge.data.model.Hotel
import com.br.natanbrito.challenge.data.model.HotelNetworkResult
import com.br.natanbrito.challenge.data.repository.HotelRepositoryImpl
import com.br.natanbrito.challenge.domain.usecases.GetHotelsUseCase
import com.br.natanbrito.challenge.domain.usecases.GetHotelsUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class GetHotelsUseCaseImplTest {

    private lateinit var useCase: GetHotelsUseCase
    private val repository: HotelRepositoryImpl = mockk()
    private val hotel: Hotel = mockk()
    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        useCase = GetHotelsUseCaseImpl(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Check getHotels is successful`() = dispatcher.runBlockingTest {
        coEvery { repository.getHotels() } returns HotelNetworkResult.Success(hotel)

        val result = kotlin.runCatching {
            useCase.invoke()
        }

        coVerify { repository.getHotels() }

        assert(result.isSuccess)
    }
}
