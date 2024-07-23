package com.example.hurb_challenge.repository

import com.example.hurb_challenge.Mocks
import com.example.hurb_challenge.app.data.ApiService
import com.example.hurb_challenge.app.data.ConnectionFailure
import com.example.hurb_challenge.app.data.Fetching
import com.example.hurb_challenge.app.data.HttpFailure
import com.example.hurb_challenge.app.data.Success
import com.example.hurb_challenge.app.data.repository.CharacterDetailsRepositoryImpl
import com.example.hurb_challenge.app.domain.repository.CharacterDetailsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import java.net.UnknownHostException

class CharacterRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var repository: CharacterDetailsRepository

    @Before
    fun setUp() {
        apiService = mockk<ApiService>()
        repository = CharacterDetailsRepositoryImpl(apiService)
    }

    @Test
    fun `should first emit Fetching then emit Success of Vehicle`() = runTest {
        val expectedResponse = Mocks.listOfVehicleDto
        val fakeUrls = listOf(
            "http://fakevehicleurl.com/01",
            "http://fakevehicleurl.com/02",
            "http://fakevehicleurl.com/03"
        ).apply {
            forEachIndexed { index, url ->
                coEvery { apiService.getVehicle(url) } returns expectedResponse[index]
            }
        }

        val result = repository.getVehicles(fakeUrls)
        result.collect {
            when (it) {
                is Success -> {
                    Assert.assertEquals(it.value.size, expectedResponse.size)
                    it.value.forEachIndexed { index, vehicle ->
                        Assert.assertEquals(vehicle.name, expectedResponse[index].name)
                        Assert.assertEquals(vehicle.model, expectedResponse[index].model)
                    }
                }
                else -> { }
            }
        }

        val resultTypes = result.toList()

        Assert.assertTrue(resultTypes[0] is Fetching)
        Assert.assertTrue(resultTypes[1] is Success)

        coVerify {
            fakeUrls.forEach {
                apiService.getVehicle(it)
            }
        }
    }

    @Test
    fun `should first emit Fetching then emit Success of Starship`() = runTest {
        val expectedResponse = Mocks.listOfStarshipsDto
        val fakeUrls = listOf(
            "http://fakestarshipurl.com/01",
            "http://fakestarshipurl.com/02",
            "http://fakestarshipurl.com/03"
        ).apply {
            forEachIndexed { index, url ->
                coEvery { apiService.getStarship(url) } returns expectedResponse[index]
            }
        }

        val result = repository.getStarships(fakeUrls)
        result.collect {
            when (it) {
                is Success -> {
                    Assert.assertEquals(it.value.size, expectedResponse.size)
                    it.value.forEachIndexed { index, starship ->
                        Assert.assertEquals(starship.name, expectedResponse[index].name)
                        Assert.assertEquals(starship.model, expectedResponse[index].model)
                    }
                }
                else -> { }
            }
        }

        val resultTypes = result.toList()

        Assert.assertTrue(resultTypes[0] is Fetching)
        Assert.assertTrue(resultTypes[1] is Success)

        coVerify {
            fakeUrls.forEach {
                apiService.getStarship(it)
            }
        }
    }

    @Test
    fun `should first emit Fetching then emit HttpFailure when call getVehicle`() = runTest {
        val expectedResponse = mockk<HttpException> {
            every { code() } returns 404
        }
        val fakeUrl = "http://fakevehicleurl.com/01"
        coEvery { apiService.getVehicle(fakeUrl) } throws expectedResponse

        val result = repository.getVehicles(listOf(fakeUrl))
        val resultType = result.toList()

        Assert.assertEquals(resultType[0], Fetching)
        Assert.assertEquals(resultType[1], HttpFailure(404))

        coVerify { apiService.getVehicle(fakeUrl) }
    }

    @Test
    fun `should first emit Fetching then emit ConnectionFailure when call getVehicle`() = runTest {
        val expectedResponse = mockk<UnknownHostException>()
        val fakeUrl = "http://fakevehicleurl.com/01"
        coEvery { apiService.getVehicle(fakeUrl) } throws expectedResponse

        val result = repository.getVehicles(listOf(fakeUrl))
        val resultType = result.toList()

        Assert.assertEquals(resultType[0], Fetching)
        Assert.assertEquals(resultType[1], ConnectionFailure)

        coVerify { apiService.getVehicle(fakeUrl) }
    }

    @Test
    fun `should first emit Fetching then emit HttpFailure when call getStarship`() = runTest {
        val expectedResponse = mockk<HttpException> {
            every { code() } returns 404
        }
        val fakeUrl = "http://fakestarshipurl.com/01"
        coEvery { apiService.getStarship(fakeUrl) } throws expectedResponse

        val result = repository.getStarships(listOf(fakeUrl))
        val resultType = result.toList()

        Assert.assertEquals(resultType[0], Fetching)
        Assert.assertEquals(resultType[1], HttpFailure(404))

        coVerify { apiService.getStarship(fakeUrl) }
    }

    @Test
    fun `should first emit Fetching then emit ConnectionFailure when call getStarship`() = runTest {
        val expectedResponse = mockk<UnknownHostException>()
        val fakeUrl = "http://fakestarshipurl.com/01"
        coEvery { apiService.getStarship(fakeUrl) } throws expectedResponse

        val result = repository.getStarships(listOf(fakeUrl))
        val resultType = result.toList()

        Assert.assertEquals(resultType[0], Fetching)
        Assert.assertEquals(resultType[1], ConnectionFailure)

        coVerify { apiService.getStarship(fakeUrl) }
    }
}