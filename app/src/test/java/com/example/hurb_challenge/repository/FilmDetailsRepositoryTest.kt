package com.example.hurb_challenge.repository

import com.example.hurb_challenge.Mocks
import com.example.hurb_challenge.app.data.ApiService
import com.example.hurb_challenge.app.data.Fetching
import com.example.hurb_challenge.app.data.HttpFailure
import com.example.hurb_challenge.app.data.Success
import com.example.hurb_challenge.app.data.repository.FilmDetailsRepositoryImpl
import com.example.hurb_challenge.app.domain.repository.FilmDetailsRepository
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

class FilmDetailsRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var repository: FilmDetailsRepository

    @Before
    fun setUp() {
        apiService = mockk<ApiService>()
        repository = FilmDetailsRepositoryImpl(apiService)
    }

    @Test
    fun `should first emit Fetching then emit a Success`() = runTest {
        val expectedResponse = Mocks.listOfCharactersDto
        val fakeUrls = listOf(
            "http://fakecharacterurl.com/01",
            "http://fakecharacterurl.com/02",
            "http://fakecharacterurl.com/03"
        ).apply {
            forEachIndexed { index, url ->
                coEvery { apiService.getCharacter(url) } returns expectedResponse[index]
            }
        }

        val result = repository.getCharactersByUrls(fakeUrls)

        result.collect {
            when (it) {
                is Success -> {
                    it.value.forEachIndexed { index, character ->
                        Assert.assertEquals(character.name, expectedResponse[index].name)
                        Assert.assertEquals(character.height, expectedResponse[index].height)
                        Assert.assertEquals(character.birthYear, expectedResponse[index].birthYear)

                        Assert.assertNotEquals(character.starshipsUrl, expectedResponse[index].starshipsUrl)
                        Assert.assertNotEquals(character.vehiclesUrl, expectedResponse[index].vehiclesUrl)
                    }
                }
                else -> {}
            }
        }

        val resultTypes = result.toList()

        Assert.assertTrue(resultTypes[0] is Fetching)
        Assert.assertTrue(resultTypes[1] is Success)

        coVerify {
            fakeUrls.forEach {
                apiService.getCharacter(it)
            }
        }
    }

    @Test
    fun `should first emit Fetching then emit a HttpFailure`() = runTest {
        val expectedResponse = mockk<HttpException> {
            every { code() } returns 404
        }
        val fakeUrl = "http://fakecharacterurl.com/01"
        coEvery { apiService.getCharacter(fakeUrl) } throws expectedResponse

        val result = repository.getCharactersByUrls(listOf(fakeUrl))

        val resultTypes = result.toList()

        Assert.assertTrue(resultTypes[0] is Fetching)
        Assert.assertEquals(resultTypes[1], HttpFailure(404))

        coVerify {
            apiService.getCharacter(fakeUrl)
        }
    }
}