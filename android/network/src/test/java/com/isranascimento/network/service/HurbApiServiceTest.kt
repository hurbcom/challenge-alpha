package com.isranascimento.network.service

import com.google.common.truth.Truth.assertThat
import com.isranascimento.network.response.NetworkResponse
import com.isranascimento.network.util.createRestClient
import com.isranascimento.network.util.mockResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class HurbApiServiceTest {
    private val mockWebServer = MockWebServer()

    private lateinit var sut: HurbApiService

    @Before
    fun setup() {
        mockWebServer.start()
        sut = HurbApiService(createRestClient(mockWebServer))
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `WHEN getHotels is called successfully THEN it returns the expected hotels list`(): Unit = runBlocking {
        mockWebServer.enqueue(mockResponse("hotels.json"))
        val response = sut.getHotelsList()
        assertThat(response).isInstanceOf(NetworkResponse.Success::class.java)

        response as NetworkResponse.Success<*>
        // TODO: assert DTO
    }

    @Test
    fun `WHEN getHotels is called with error THEN it returns generic error`() = runBlocking {
        mockWebServer.shutdown()
        val response = sut.getHotelsList()
        assertThat(response).isInstanceOf(NetworkResponse.GenericError::class.java)
    }
}