package com.example.hurb_challenge

import com.example.hurb_challenge.app.data.Fetching
import com.example.hurb_challenge.app.data.Success
import com.example.hurb_challenge.app.data.UnknownFailure
import com.example.hurb_challenge.app.domain.toCardItem
import com.example.hurb_challenge.app.domain.usecase.GetCharacterStarshipsUseCase
import com.example.hurb_challenge.app.domain.usecase.GetCharacterVehiclesUseCase
import com.example.hurb_challenge.app.presentation.characterdetails.CharacterDetailsViewModel
import com.example.hurb_challenge.app.presentation.common.Empty
import com.example.hurb_challenge.app.presentation.common.Error
import com.example.hurb_challenge.app.presentation.common.Loaded
import com.example.hurb_challenge.app.presentation.common.Loading
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailsViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var getCharacterVehiclesUseCase: GetCharacterVehiclesUseCase
    private lateinit var getCharacterStarshipsUseCase: GetCharacterStarshipsUseCase
    private lateinit var viewModel: CharacterDetailsViewModel

    @Before
    fun setUp() {
        getCharacterVehiclesUseCase = mockk()
        getCharacterStarshipsUseCase = mockk()
        viewModel = CharacterDetailsViewModel(getCharacterVehiclesUseCase, getCharacterStarshipsUseCase, testDispatcher)
    }

    @Test
    fun `vehicles state flow should be Empty`() = runTest {
        Assert.assertEquals(viewModel.characterVehiclesStateFlow.value, Empty)
    }

    @Test
    fun `vehicles state flow should be Loading`() = runTest {
        val fakeUrls = listOf("http://fakeurl1.com", "http://fakeurl2.com")
        coEvery { getCharacterVehiclesUseCase.call(fakeUrls) } returns flowOf(Fetching)

        viewModel.loadCharacterVehicles(fakeUrls)

        Assert.assertEquals(viewModel.characterVehiclesStateFlow.value, Loading)
    }

    @Test
    fun `vehicles state flow should be Loaded`() = runTest {
        val fakeUrls = listOf("http://fakeurl1.com", "http://fakeurl2.com")
        val vehicles = Mocks.listOfVehicle
        coEvery { getCharacterVehiclesUseCase.call(fakeUrls) } returns flowOf(Success(vehicles))

        viewModel.loadCharacterVehicles(fakeUrls)
        val cardItems = vehicles.map { it.toCardItem() }
        Assert.assertEquals(viewModel.characterVehiclesStateFlow.value, Loaded(cardItems))
    }

    @Test
    fun `vehicles state flow should be Error`() = runTest {
        val fakeUrls = listOf("http://fakeurl1.com", "http://fakeurl2.com")
        coEvery { getCharacterVehiclesUseCase.call(fakeUrls) } returns flowOf(UnknownFailure)

        viewModel.loadCharacterVehicles(fakeUrls)

        Assert.assertEquals(viewModel.characterVehiclesStateFlow.value, Error("Unknown error"))
    }

    @Test
    fun `starships state flow should be Empty`() = runTest {
        Assert.assertEquals(viewModel.characterStarshipsStateFlow.value, Empty)
    }

    @Test
    fun `starships state flow should be Loading`() = runTest {
        val fakeUrls = listOf("http://fakeurl1.com", "http://fakeurl2.com")
        coEvery { getCharacterStarshipsUseCase.call(fakeUrls) } returns flowOf(Fetching)

        viewModel.loadCharacterStarships(fakeUrls)

        Assert.assertEquals(viewModel.characterStarshipsStateFlow.value, Loading)
    }

    @Test
    fun `starships state flow should be Loaded`() = runTest {
        val fakeUrls = listOf("http://fakeurl1.com", "http://fakeurl2.com")
        val starships = Mocks.listOfStarship
        coEvery { getCharacterStarshipsUseCase.call(fakeUrls) } returns flowOf(Success(starships))

        viewModel.loadCharacterStarships(fakeUrls)

        val cardItems = starships.map { it.toCardItem() }
        Assert.assertEquals(viewModel.characterStarshipsStateFlow.value, Loaded(cardItems))
    }

    @Test
    fun `starships state flow should be Error`() = runTest {
        val fakeUrls = listOf("http://fakeurl1.com", "http://fakeurl2.com")
        coEvery { getCharacterStarshipsUseCase.call(fakeUrls) } returns flowOf(UnknownFailure)

        viewModel.loadCharacterStarships(fakeUrls)

        Assert.assertEquals(viewModel.characterStarshipsStateFlow.value, Error("Unknown error"))
    }
}