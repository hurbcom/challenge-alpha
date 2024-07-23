package com.example.hurb_challenge

import com.example.hurb_challenge.app.data.Fetching
import com.example.hurb_challenge.app.data.Success
import com.example.hurb_challenge.app.data.UnknownFailure
import com.example.hurb_challenge.app.domain.toCardItem
import com.example.hurb_challenge.app.domain.usecase.GetCharactersInFilmUseCase
import com.example.hurb_challenge.app.presentation.common.Empty
import com.example.hurb_challenge.app.presentation.common.Error
import com.example.hurb_challenge.app.presentation.common.Loaded
import com.example.hurb_challenge.app.presentation.common.Loading
import com.example.hurb_challenge.app.presentation.filmdetails.FilmDetailsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FilmDetailsViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var useCase: GetCharactersInFilmUseCase
    private lateinit var viewModel: FilmDetailsViewModel

    @Before
    fun setUp() {
        useCase = mockk()
        viewModel = FilmDetailsViewModel(useCase, testDispatcher)
    }

    @Test
    fun `characters state flow should be Empty`() = runTest {
        Assert.assertEquals(viewModel.charactersStateFlow.value, Empty)
    }

    @Test
    fun `vehicles state flow should be Loading`() = runTest {
        val fakeUrls = listOf("http://fakeurl1.com", "http://fakeurl2.com")
        coEvery { useCase.call(fakeUrls) } returns flow { emit(Fetching) }

        viewModel.loadCharactersByUrl(fakeUrls)

        Assert.assertEquals(viewModel.charactersStateFlow.value, Loading)
    }

    @Test
    fun `vehicles state flow should be Loaded`() = runTest {
        val fakeUrls = listOf("http://fakeurl1.com", "http://fakeurl2.com")
        val characters = Mocks.listOfCharacter
        coEvery { useCase.call(fakeUrls) } returns flow { emit(Success(characters)) }

        viewModel.loadCharactersByUrl(fakeUrls)

        val cardItems = characters.map { it.toCardItem() }
        Assert.assertEquals(viewModel.charactersStateFlow.value, Loaded(cardItems))
    }

    @Test
    fun `vehicles state flow should be Error`() = runTest {
        val fakeUrls = listOf("http://fakeurl1.com", "http://fakeurl2.com")
        coEvery { useCase.call(fakeUrls) } returns flow { emit(UnknownFailure) }

        viewModel.loadCharactersByUrl(fakeUrls)

        Assert.assertEquals(viewModel.charactersStateFlow.value, Error("Unknown error"))
    }
}