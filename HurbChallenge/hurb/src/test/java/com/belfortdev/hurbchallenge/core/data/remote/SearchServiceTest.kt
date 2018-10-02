package com.belfortdev.hurbchallenge.core.data.remote

import com.belfortdev.hurbchallenge.core.test.TestDependencyProvider
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

/**
 * Tests for [SearchService]
 */
class SearchServiceTest {

    private lateinit var searchService: SearchService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun init() {
        mockWebServer = MockWebServer()
        searchService = TestDependencyProvider
                .getRetrofit(mockWebServer.url("/"))
                .create(SearchService::class.java)

    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getAllHotelsFromRio() {
        queueResponse {
            setResponseCode(200)
            setBody(TestDependencyProvider.getResponseFromJson("dummy-api-response"))
        }

        searchService
                .getAllHotelsFromRio()
                .test()
                .run {
                    assertNoErrors()
                    assertValueCount(1)
                    val results = values()[0].results
                    Assert.assertEquals(results!!.size, 20)
                    Assert.assertEquals(results[1]!!.stars!!.toFloat(), 3.0.toFloat())
                    Assert.assertEquals(results[2]!!.isHotel, true)
                    Assert.assertEquals(results[4]!!.amenities?.size, 13)
                    Assert.assertEquals(results[5]!!.address?.city, "Cabo Frio")
                }
    }

    private fun queueResponse(block: MockResponse.() -> Unit) {
        mockWebServer.enqueue(MockResponse().apply(block))
    }

}