package com.hurb.challengealpha.api

import junit.framework.TestCase.fail
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.net.HttpURLConnection
import java.nio.charset.StandardCharsets

class ApiServiceTest {
    private var mockWebServer = MockWebServer()

    private lateinit var api: HurbApi

    //Sets up mocked web server to intercept response in test
    @Before
    fun setup() {
        mockWebServer.start()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(HurbApi::class.java)
    }

    //Shuts down mocked web server
    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    //Tests api call with mocked response
    @Test
    fun testSearch() {
        val classLoader = javaClass.classLoader
        if (classLoader != null) {
            val inputStream: InputStream? = classLoader
                .getResourceAsStream("test_search.json")
            if (inputStream != null) {
                val source = inputStream.source().buffer()
                val body = source.readString(StandardCharsets.UTF_8)
                val response = MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(body)
                mockWebServer.enqueue(response)
                val q = "buzios"
                val page = 1
                with(api.search(q, page).test()) {
                    assertNoErrors()
                    assertNoTimeout()
                    assertValueCount(1)
                    val searchResponse = values()[0]
                    assert(searchResponse.meta.query == q)
                    val result = searchResponse.results[0]
                    assert(result.isHotel || result.isTicket || result.isPackage)
                    assert(result.stars in 1..5)
                }
            } else {
                fail("Json file not found")
            }
        } else {
            fail("Class loader is null")
        }
    }
}