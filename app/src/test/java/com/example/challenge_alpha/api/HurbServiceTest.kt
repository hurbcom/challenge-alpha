package com.example.challenge_alpha.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.*
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class HurbServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: HurbService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HurbService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }


    @Suppress("BlockingMethodInNonBlockingContext")
    @Test
    fun getSuggestion() = runBlocking {
        enqueueResponse("suggestions-response.json")

        val repos = service.suggestion("Aar").body()?.suggestions

        val request = mockWebServer.takeRequest()
        Assert.assertEquals("/search/api/suggestion?q=Aar", request.path)

        Assert.assertEquals(5, repos?.size)
        Assert.assertEquals(null, repos?.firstOrNull()?.sku)
        Assert.assertEquals("city", repos?.firstOrNull()?.suggestionType)
        Assert.assertEquals("Aarhus, Midtjylland, Dinamarca", repos?.firstOrNull()?.text)
        Assert.assertEquals("city_aarhus|1,state_midtjylland|1", repos?.firstOrNull()?.filter)
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    @Test
    fun getSearch() = runBlocking {
        enqueueResponse("hotels-response.json")

        val repos = service.search("Aardvark", 1).body()?.resultDetail

        val request = mockWebServer.takeRequest()
        Assert.assertEquals("/search/api?&q=Aardvark&page=1", request.path)

        Assert.assertEquals(1, repos?.size)
        Assert.assertEquals("NHU-7987-0-0-0-0", repos?.firstOrNull()?.sku)
        Assert.assertEquals("hotel", repos?.firstOrNull()?.category)
        Assert.assertEquals(true, repos?.firstOrNull()?.hotelIs)
        Assert.assertEquals("AT7987", repos?.firstOrNull()?.id)
        Assert.assertEquals(31, repos?.firstOrNull()?.amenities?.size)
        Assert.assertEquals(363.35f, repos?.firstOrNull()?.price?.amount)
        Assert.assertEquals(3f, repos?.firstOrNull()?.stars)
        Assert.assertEquals("Gramado", repos?.firstOrNull()?.address?.city)
        Assert.assertEquals("95670000", repos?.firstOrNull()?.address?.zipCode)
        Assert.assertEquals("Rua Mestre, 18", repos?.firstOrNull()?.address?.fullAddress)
        Assert.assertEquals("Brasil", repos?.firstOrNull()?.address?.country)
    }

private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
    val inputStream =
        javaClass.classLoader!!.getResourceAsStream("api-response/$fileName").source().buffer()
    val mockResponse = MockResponse()
    for ((key, value) in headers) {
        mockResponse.addHeader(key, value)
    }

    mockWebServer.enqueue(
        mockResponse
            .setBody(inputStream.readString(Charsets.UTF_8))
    )
}

}