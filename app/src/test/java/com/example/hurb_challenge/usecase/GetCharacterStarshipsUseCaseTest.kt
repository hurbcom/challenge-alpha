package com.example.hurb_challenge.usecase

import com.example.hurb_challenge.Mocks
import com.example.hurb_challenge.app.data.Success
import com.example.hurb_challenge.app.domain.repository.CharacterDetailsRepository
import com.example.hurb_challenge.app.domain.usecase.GetCharacterStarshipsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCharacterStarshipsUseCaseTest {

    private lateinit var repository: CharacterDetailsRepository
    private lateinit var useCase: GetCharacterStarshipsUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetCharacterStarshipsUseCase(repository)
    }

    @Test
    fun `should call getStarships`() = runTest {
        val fakeUrls = listOf(
            "http://fakecharacterurl.com/01",
            "http://fakecharacterurl.com/02",
            "http://fakecharacterurl.com/03"
        )
        coEvery { repository.getStarships(fakeUrls) } returns flowOf(Success(Mocks.listOfStarship))
        useCase.call(fakeUrls)
        coVerify {
            repository.getStarships(fakeUrls)
        }
    }
}