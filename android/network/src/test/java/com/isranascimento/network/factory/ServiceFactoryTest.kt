package com.isranascimento.network.factory

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.common.truth.Truth.assertThat
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Loggable
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.http.GET

class ServiceFactoryTest {

    companion object {
        private const val RESPONSE_BODY_WITH_UNKNOWN_PROPERTY = """
          {
              "property": "property",
              "unknown_property": "unknown"
          }
        """

        private const val DEFAULT_RESPONSE_BODY = """
          {
              "property": "property"
          }
        """
    }

    private lateinit var sut: ServiceFactory
    private lateinit var mockWebServer: MockWebServer

    private var curlLoggingInterceptorLogger: Loggable = mockk()
    private var httpLoggingInterceptorLogger: HttpLoggingInterceptor.Logger = mockk()
    private lateinit var httpLoggingInterceptor: HttpLoggingInterceptor
    private lateinit var curlLoggingInterceptor: CurlInterceptor

    @Before
    fun setup() {
        httpLoggingInterceptor = HttpLoggingInterceptor(httpLoggingInterceptorLogger).apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        curlLoggingInterceptor = CurlInterceptor(curlLoggingInterceptorLogger)
        sut = ServiceFactory(false, httpLoggingInterceptor, CurlInterceptor(curlLoggingInterceptorLogger))

        mockWebServer = MockWebServer()
        mockWebServer.start()
        every { curlLoggingInterceptorLogger.log(any()) } just Runs
        every { httpLoggingInterceptorLogger.log(any()) } just Runs
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `WHEN isDebug is false THEN the factory does not display logs`() = runBlocking {
        sut.createRestClient(TestApi::class.java, mockWebServer.url("/"))
        val api = sut.createRestClient(TestApi::class.java, mockWebServer.url("/"))
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(DEFAULT_RESPONSE_BODY))
        api.testCorrectParser()

        verify(exactly = 0) {
            curlLoggingInterceptorLogger.log(any())
            httpLoggingInterceptorLogger.log(any())
        }
    }

    @Test
    fun `WHEN isDebug is true THEN the factory displays logs`() = runBlocking {
        sut = ServiceFactory(true, httpLoggingInterceptor, curlLoggingInterceptor)
        val api = sut.createRestClient(TestApi::class.java, mockWebServer.url("/"))
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(DEFAULT_RESPONSE_BODY))
        api.testCorrectParser()

        verify(atLeast = 1) {
            curlLoggingInterceptorLogger.log(any())
            httpLoggingInterceptorLogger.log(any())
        }
    }

    @Test
    fun `WHEN the server returns a correctly json THEN the response is deserialized correctly`() = runBlocking {
        val api = sut.createRestClient(TestApi::class.java, mockWebServer.url("/"))
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(DEFAULT_RESPONSE_BODY))

        val response = api.testCorrectParser()
        assertThat(response.body()?.property).isEqualTo("property")
    }

    @Test
    fun `WHEN an unknown jsonNode is added to the response THEN the deserialization does not trigger an exception`() = runBlocking {
        val api = sut.createRestClient(TestApi::class.java, mockWebServer.url("/"))
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(
            RESPONSE_BODY_WITH_UNKNOWN_PROPERTY))

        val response = api.testDoesNotTriggerException()
        assertThat(response.body()).isInstanceOf(DefaultResponse::class.java)
    }

    private data class DefaultResponse(
        @JsonProperty("property") val property: String? = null
    )

    private interface TestApi {
        @GET("/test")
        suspend fun testCorrectParser(): Response<DefaultResponse>

        @GET("/test2")
        suspend fun testDoesNotTriggerException(): Response<DefaultResponse>
    }
}