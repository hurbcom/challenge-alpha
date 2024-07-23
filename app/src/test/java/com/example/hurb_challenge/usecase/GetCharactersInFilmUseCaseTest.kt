package com.example.hurb_challenge.usecase

import com.example.hurb_challenge.Mocks
import com.example.hurb_challenge.app.data.Success
import com.example.hurb_challenge.app.domain.repository.FilmDetailsRepository
import com.example.hurb_challenge.app.domain.usecase.GetCharactersInFilmUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCharactersInFilmUseCaseTest {

    private lateinit var repository: FilmDetailsRepository
    private lateinit var useCase: GetCharactersInFilmUseCase

    @Before
    fun setUp() {
        repository = mockk<FilmDetailsRepository>()
        useCase = GetCharactersInFilmUseCase(repository)
    }

    @Test
    fun `should call getCharactersByUrls`() = runTest {
        val fakeUrls = listOf(
            "http://fakecharacterurl.com/01",
            "http://fakecharacterurl.com/02",
            "http://fakecharacterurl.com/03"
        )
        coEvery { repository.getCharactersByUrls(fakeUrls) } returns flowOf(Success(Mocks.listOfCharacter))
        useCase.call(fakeUrls)
        coVerify {
            repository.getCharactersByUrls(fakeUrls)
        }
    }
}