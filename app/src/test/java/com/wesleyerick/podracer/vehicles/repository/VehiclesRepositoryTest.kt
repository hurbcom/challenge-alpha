package com.wesleyerick.podracer.vehicles.repository

import com.wesleyerick.podracer.CoroutinesTestRule
import com.wesleyerick.podracer.data.model.ListData
import com.wesleyerick.podracer.data.model.vehicles.Vehicle
import com.wesleyerick.podracer.data.repository.vehicles.IRepositoryVehicles
import com.wesleyerick.podracer.data.repository.vehicles.VehiclesRepository
import com.wesleyerick.podracer.data.service.VehiclesService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Response

class VehiclesRepositoryTest : KoinTest {

    private companion object {
        const val MOCK_ID = "1"
        const val MOCK_COUNT = 0
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    private val service: VehiclesService = mockk()
    private val repository: IRepositoryVehicles by inject()


    @Before
    fun setup() {
        startKoin {
            modules(module {
                single { VehiclesRepository(service) }
            })
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getAll success`() = runTest {
        val expectedResponse = Response.success(
            ListData<Vehicle>(
                count = MOCK_COUNT,
                next = String(),
                previous = String(),
                results = emptyList()
            )
        )
        coEvery { service.getAll() } returns expectedResponse

        val result = repository.getAll()

        assertEquals(expectedResponse, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getDetails success`() = runTest {
        val expectedResponse = Response.success(Vehicle())
        coEvery { service.getDetails(MOCK_ID) } returns expectedResponse
        val result = repository.getDetails(MOCK_ID)
        assertEquals(expectedResponse, result)
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}