package com.example.starwars

import androidx.lifecycle.Observer
import com.example.starwars.data.repository.PeoplesRepository
import com.example.starwars.presentation.feature.listpeople.ListPeopleViewModel
import com.example.starwars.presentation.model.Item
import com.example.starwars.presentation.model.toListPeople
import com.example.starwars.retrofit.ApiResult
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(InstantExecutorExtension::class)
@OptIn(ExperimentalCoroutinesApi::class)

class ListPeopleViewModelTest {
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: ListPeopleViewModel
    private var peoplesRepository = mockk<PeoplesRepository>()
    private var actualPeopleListLiveDataObserver: Observer<List<Item>> = mockk {
        every { onChanged(listOf(people)) } just Runs
    }
    private var loadingLiveDataObserver: Observer<Boolean> = mockk {
        every { onChanged(false) } just Runs
    }


    private val messageError = "error"
    private val errorObserver: Observer<String> = mockk {
        every { onChanged(messageError) } just Runs
    }

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = ListPeopleViewModel(peoplesRepository).apply {
            requestError.observeForever(errorObserver)
        }
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `every call peopleRepository should active errorLiveData`() =
        runTest(UnconfinedTestDispatcher()) {
            coEvery { peoplesRepository.getPeoples("1") } returns
                    flowOf(
                        ApiResult.Error(Throwable(messageError))
                    )
            loadingLiveDataObserver = mockk {
                every { onChanged(false) } just Runs
            }
            viewModel.getItems("1")
            viewModel.loadingLiveData.observeForever(loadingLiveDataObserver)

            verify { loadingLiveDataObserver.onChanged(false) }
            verify { errorObserver.onChanged(messageError) }
        }

    @Test
    fun `every call peopleRepository with next and previous page null should returns success with previous and next livedatas with value 0`() =
        runTest(UnconfinedTestDispatcher()) {

            val nextPageLiveDataObserver: Observer<String> = mockk {
                every { onChanged("0") } just Runs
            }
            val previousPageLiveDataObserver: Observer<String> = mockk {
                every { onChanged("0") } just Runs
            }

            coEvery { peoplesRepository.getPeoples("1") } returns
                    flowOf(
                        ApiResult.Success(pagePeople)
                    )

            loadingLiveDataObserver = mockk {
                every { onChanged(false) } just Runs
            }
            viewModel.getItems("1")
            viewModel.actualItemListLiveData.observeForever(actualPeopleListLiveDataObserver)
            viewModel.loadingLiveData.observeForever(loadingLiveDataObserver)
            viewModel.nextPageLiveData.observeForever(nextPageLiveDataObserver)
            viewModel.previousPageLiveData.observeForever(previousPageLiveDataObserver)


            verify { nextPageLiveDataObserver.onChanged("0") }
            verify { previousPageLiveDataObserver.onChanged("0") }
            verify { loadingLiveDataObserver.onChanged(false) }
            verify { actualPeopleListLiveDataObserver.onChanged(listOf(people)) }
        }

    @Test
    fun `every call peopleRepository with actual page 2 should returns success with values previous 1 and next 2`() =
        runTest(UnconfinedTestDispatcher()) {
            val nextPageLiveDataObserver: Observer<String> = mockk {
                every { onChanged("3") } just Runs
            }
            val previousPageLiveDataObserver: Observer<String> = mockk {
                every { onChanged("1") } just Runs
            }
            coEvery { peoplesRepository.getPeoples("2") } returns
                    flowOf(
                        ApiResult.Success(pagePeople.copy(nextPage = "3", previousPage = "1"))
                    )
            loadingLiveDataObserver = mockk {
                every { onChanged(false) } just Runs
            }
            viewModel.getItems("2")
            viewModel.actualItemListLiveData.observeForever(actualPeopleListLiveDataObserver)
            viewModel.loadingLiveData.observeForever(loadingLiveDataObserver)
            viewModel.nextPageLiveData.observeForever(nextPageLiveDataObserver)
            viewModel.previousPageLiveData.observeForever(previousPageLiveDataObserver)

            verify { loadingLiveDataObserver.onChanged(false) }
            verify { actualPeopleListLiveDataObserver.onChanged(listOf(people)) }
        }

    @Test
    fun `every call peopleRepository should returns loading true`() =
        runTest(UnconfinedTestDispatcher()) {
            coEvery { peoplesRepository.getPeoples("1") } returns
                    flowOf(
                        ApiResult.Loading
                    )
            loadingLiveDataObserver = mockk {
                every { onChanged(true) } just Runs
            }
            viewModel.loadingLiveData.observeForever(loadingLiveDataObserver)
            viewModel.getItems("1")
            verify { loadingLiveDataObserver.onChanged(true) }

        }
}