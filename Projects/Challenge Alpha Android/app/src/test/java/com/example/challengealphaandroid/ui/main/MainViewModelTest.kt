package com.example.challengealphaandroid.ui.main

import com.example.challengealphaandroid.domain.PeopleManager
import com.example.challengealphaandroid.domain.PlanetManager
import com.example.challengealphaandroid.domain.StarshipManager
import com.example.challengealphaandroid.model.*
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @RelaxedMockK
    private lateinit var peopleManager: PeopleManager

    @RelaxedMockK
    private lateinit var planetManager: PlanetManager

    @RelaxedMockK
    private lateinit var starshipManager: StarshipManager

    @RelaxedMockK
    private lateinit var mockPeopleList: List<Person>

    @RelaxedMockK
    private lateinit var mockPlanetList: List<Planet>

    @RelaxedMockK
    private lateinit var mockPerson: Person

    @RelaxedMockK
    private lateinit var mockPlanet: Planet

    @RelaxedMockK
    private lateinit var mockStarship: Starship

    @RelaxedMockK
    private lateinit var mockStarshipList: List<Starship>

    private lateinit var dispatcher: TestDispatcher

    private lateinit var MainViewModel: MainViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)

        mockkStatic(Dispatchers::class)
        every {
            Dispatchers.Default
        } returns dispatcher

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `when lists are fetch successfully, then viewModel should contain the lists and should not be in progress and error should be null`() =
        runTest {

            coEvery {
                peopleManager.getAllItens()
            } returns flow { emit(ResultWithStatus.success(mockPeopleList)) }

            coEvery {
                planetManager.getAllItens()
            } returns flow { emit(ResultWithStatus.success(mockPlanetList)) }

            coEvery {
                starshipManager.getAllItens()
            } returns flow { emit(ResultWithStatus.success(mockStarshipList)) }

            MainViewModel = MainViewModel(peopleManager, starshipManager, planetManager)

            advanceUntilIdle()

            MainViewModel.state.value.apply {
                Assert.assertEquals(
                    peopleList,
                    mockPeopleList
                )

                Assert.assertEquals(
                    planetList,
                    mockPlanetList
                )

                Assert.assertEquals(
                    starshipList,
                    mockStarshipList
                )

                Assert.assertEquals(
                    inProgress,
                    false
                )

                Assert.assertEquals(
                    error,
                    null
                )
            }

        }

    @Test
    fun `when any list is  not fetch successfully, then viewModel should contain error and should not be in progress and list should be null`() =
        runTest {

            coEvery {
                peopleManager.getAllItens()
            } returns flow { emit(ResultWithStatus.error("any message")) }

            MainViewModel = MainViewModel(peopleManager, starshipManager, planetManager)

            advanceUntilIdle()

            MainViewModel.state.value.apply {
                Assert.assertEquals(
                    peopleList,
                    null
                )

                Assert.assertEquals(
                    inProgress,
                    false
                )

                Assert.assertEquals(
                    error,
                    "any message"
                )
            }

        }

    @Test
    fun `if any list is still in progress and there is no error, then viewModel should not contain error and should be in progress`() =
        runTest {

            MainViewModel = MainViewModel(peopleManager, starshipManager, planetManager)

            advanceUntilIdle()

            MainViewModel.state.value.apply {
                Assert.assertEquals(
                    peopleList,
                    null
                )

                Assert.assertEquals(
                    inProgress,
                    true
                )

                Assert.assertEquals(
                    error,
                    null
                )
            }

        }

    @Test
    fun `updatePersonFavorite test`() =
        runTest {

            MainViewModel = MainViewModel(peopleManager, starshipManager, planetManager)

            MainViewModel.updatePersonFavorite(mockPerson, true)

            advanceUntilIdle()

            coVerify {
                peopleManager.updatePersonFavorite(mockPerson, true)
            }

        }

    @Test
    fun `updatePlanetFavorite test`() =
        runTest {

            MainViewModel = MainViewModel(peopleManager, starshipManager, planetManager)

            MainViewModel.updatePlanetFavorite(mockPlanet, false)

            advanceUntilIdle()

            coVerify {
                planetManager.updatePlanetFavorite(mockPlanet, false)
            }

        }

    @Test
    fun `updateStarshipFavorite test`() =
        runTest {

            MainViewModel = MainViewModel(peopleManager, starshipManager, planetManager)

            MainViewModel.updateStarshipFavorite(mockStarship, true)

            advanceUntilIdle()

            coVerify {
                starshipManager.updateStarshipFavorite(mockStarship, true)
            }

        }

    @Test
    fun `when cache lists are fetch successfully after a loadFromCache call, then viewModel should contain the lists and should not be in progress and error should be null`() =
        runTest {

            coEvery {
                peopleManager.loadCache()
            } returns mockPeopleList

            coEvery {
                planetManager.loadCache()
            } returns mockPlanetList

            coEvery {
                starshipManager.loadCache()
            } returns mockStarshipList

            MainViewModel = MainViewModel(peopleManager, starshipManager, planetManager)

            MainViewModel.loadFromCache()

            advanceUntilIdle()

            MainViewModel.state.value.apply {
                Assert.assertEquals(
                    peopleList,
                    mockPeopleList
                )

                Assert.assertEquals(
                    planetList,
                    mockPlanetList
                )

                Assert.assertEquals(
                    starshipList,
                    mockStarshipList
                )

                Assert.assertEquals(
                    inProgress,
                    false
                )

                Assert.assertEquals(
                    error,
                    null
                )
            }

        }

    @Test
    fun `whena cache lists are fetch successfully after a loadFromCache call, then viewModel should contain the lists and should not be in progress and error should be null`() =
        runTest {

            val dummyPeopleList = listOf(Person("luke", "1999", Homeworld("earth"), 1), Person("darth", "1999", Homeworld("earth"), 1))
            val dummyExpectedPeopleList = listOf(Person("luke", "1999", Homeworld("earth"), 1))
            coEvery {
                peopleManager.getAllItens()
            } returns flow { emit(ResultWithStatus.success(dummyPeopleList)) }

            coEvery {
                planetManager.getAllItens()
            } returns flow { emit(ResultWithStatus.success(mockPlanetList)) }

            coEvery {
                starshipManager.getAllItens()
            } returns flow { emit(ResultWithStatus.success(mockStarshipList)) }

            MainViewModel = MainViewModel(peopleManager, starshipManager, planetManager)

            advanceUntilIdle()

            MainViewModel.filterPeople("lu")
            MainViewModel.filterPlanet("")

            MainViewModel.state.value.apply {
                Assert.assertEquals(
                    peopleList,
                    dummyExpectedPeopleList
                )

                Assert.assertEquals(
                    planetList,
                    mockPlanetList
                )

                Assert.assertEquals(
                    starshipList,
                    mockStarshipList
                )

                Assert.assertEquals(
                    inProgress,
                    false
                )

                Assert.assertEquals(
                    error,
                    null
                )
            }

        }

}