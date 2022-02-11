package com.isranascimento.network.service

import com.isranascimento.network.restclient.HotelsRestClient
import com.isranascimento.network.util.createRestClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class HotelsApiServiceTest {
    private val mockWebServer = MockWebServer()

    private lateinit var sut: HotelsApiService

    @Before
    fun setup() {
        sut = HotelsApiService(createRestClient(mockWebServer))
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `WHEN getHotels is called successfully THEN it returns the expected hotels list`() {

    }

    @Test
    fun `WHEN getHotels is called with error THEN it returns generic error`() {

    }
}