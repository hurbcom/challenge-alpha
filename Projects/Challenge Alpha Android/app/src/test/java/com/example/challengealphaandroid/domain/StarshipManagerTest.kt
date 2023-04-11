package com.example.challengealphaandroid.domain

import com.example.challengealphaandroid.GetStarshipsQuery
import com.example.challengealphaandroid.data.LocalStarshipRepository
import com.example.challengealphaandroid.data.RemoteApolloRepository
import com.example.challengealphaandroid.data.RemoteRestRepository
import com.example.challengealphaandroid.model.*
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class StarshipManagerTest {

    @RelaxedMockK
    private lateinit var localStarshipRepository: LocalStarshipRepository

    @RelaxedMockK
    private lateinit var remoteApolloRepository: RemoteApolloRepository

    @RelaxedMockK
    private lateinit var remoteRestRepository: RemoteRestRepository

    private lateinit var dispatcher: TestDispatcher

    private lateinit var starshipManager: StarshipManager

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
    fun `test getAllItems with cache`() = runBlocking {
        starshipManager =
            StarshipManager(remoteApolloRepository, localStarshipRepository, remoteRestRepository)
        val cache = listOf(Starship("Starship A", "Model A", "Class A"))
        coEvery { localStarshipRepository.getStarshipCache() } returns cache
        var result: ResultWithStatus<List<Starship>>? = null
        result = starshipManager.getAllItens().first()
        Assert.assertEquals(ResultWithStatus(Status.SUCCESS, cache).data, result.data)
    }

    @Test
    fun `loadCache should return the result from local repository`() = runBlocking {
        starshipManager =
            StarshipManager(remoteApolloRepository, localStarshipRepository, remoteRestRepository)
        val expected = listOf(
            Starship("starship1", "model1", "class1"),
            Starship("starship2", "model2", "class2")
        )
        coEvery { localStarshipRepository.getStarshipCache() } returns expected


        val result = starshipManager.loadCache()

        Assert.assertEquals(expected, result)
    }

    @Test
    fun `updateStarshipFavorite should update the starship on local repository`() = runBlocking {
        starshipManager =
            StarshipManager(remoteApolloRepository, localStarshipRepository, remoteRestRepository)
        val starship = Starship("starship1", "model1", "class1")
        val isFavorite = true
        coEvery {
            localStarshipRepository.updateStarship(starship.apply {
                this.isFavorite = isFavorite
            })
        } just Runs

        starshipManager.updateStarshipFavorite(starship, isFavorite)

        coVerify {
            localStarshipRepository.updateStarship(starship.apply {
                this.isFavorite = isFavorite
            })
        }
    }


}