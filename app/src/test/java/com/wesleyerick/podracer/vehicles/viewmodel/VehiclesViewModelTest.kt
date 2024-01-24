package com.wesleyerick.podracer.vehicles.viewmodel

import androidx.lifecycle.Observer
import com.wesleyerick.podracer.CoroutinesTestRule
import com.wesleyerick.podracer.data.model.vehicles.Vehicle
import com.wesleyerick.podracer.domain.usecase.vehicles.VehiclesUseCases
import com.wesleyerick.podracer.view.vehicles.VehiclesViewModel
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class VehiclesViewModelTest : KoinTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcher = CoroutinesTestRule()

    private val useCase: VehiclesUseCases = mockk()
    private val viewModel: VehiclesViewModel by inject()

    private val mockObserver: Observer<List<Vehicle>> = mockk(relaxed = true)

    @Before
    fun setup() {
        val myModule = module {
            single { useCase }
            viewModel { VehiclesViewModel(get()) }
        }
        startKoin { modules(myModule) }

        viewModel.vehiclesList.observeForever(mockObserver)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `execute getList function return`() = runTest {
        viewModel.getList()

        verify { mockObserver.onChanged(listOf(Vehicle(String(), String(), String()))) }
        assertEquals(listOf(Vehicle(String(), String(), String())), viewModel.vehiclesList.value)
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}