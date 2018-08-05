package com.github.felipehjcosta.huchallenge.base.hotels

import com.github.felipehjcosta.huchallenge.base.utils.readResourceFile
import io.reactivex.observers.TestObserver
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After

import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkHotelsRepositoryTest {

    private val mockWebServer = MockWebServer()

    private val baseUrl: String = "http://${mockWebServer.hostName}:${mockWebServer.port}"

    private val httpClient: OkHttpClient = OkHttpClient.Builder().build()
    private val searchApi: SearchApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build().create(SearchApi::class.java)

    private val networkDataRepository = NetworkHotelsRepository(searchApi)

    @After
    fun tearDown() = mockWebServer.shutdown()

    @Test
    fun ensureFetchHotelsDeliverCorrectly() {

        val body = readResourceFile("integrationTest/results.json")

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(body))

        val itemObserver = TestObserver.create<List<String>>()
        networkDataRepository.fetchHotels().subscribe(itemObserver)

        itemObserver.awaitTerminalEvent()

        itemObserver.assertValue { it.size == 20 }
        itemObserver.assertValue { it[0] == "Hotel Vilamar Copacabana" }

        itemObserver.dispose()
    }
}