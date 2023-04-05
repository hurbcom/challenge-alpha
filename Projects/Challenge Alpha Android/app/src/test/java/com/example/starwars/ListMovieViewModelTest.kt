package com.example.starwars

import androidx.lifecycle.Observer
import com.example.starwars.data.repository.MoviesRepository
import com.example.starwars.presentation.feature.listmovie.ListMovieViewModel
import com.example.starwars.presentation.feature.listpeople.ListPeopleViewModel
import com.example.starwars.presentation.model.Item
import com.example.starwars.retrofit.ApiResult
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class ListMovieViewModelTest {
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: ListMovieViewModel
    private var moviesRepository = mockk<MoviesRepository>()
    private var actualMovieListLiveDataObserver: Observer<List<Item>> = mockk {
        every { onChanged(listOf( movie)) } just Runs
    }
    private var loadingLiveDataObserver: Observer<Boolean> = mockk {
        every { onChanged(false) }just Runs
        every { onChanged(true) } just Runs
    }


    private val messageError = "error"
    private val errorObserver: Observer<String> = mockk {
        every { onChanged(messageError) } just Runs
    }

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = ListMovieViewModel(moviesRepository).apply {
            requestError.observeForever(errorObserver)
        }
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `every call movieRepository should active errorLiveData`() =
        runTest(UnconfinedTestDispatcher()) {
            coEvery { moviesRepository.getMovies("1") } returns
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
    fun `every call movieRepository with next and previous page null should returns success with previous and next livedatas with value 0`() =
        runTest(UnconfinedTestDispatcher()) {

            val nextPageLiveDataObserver: Observer<String> = mockk {
                every { onChanged("0") } just Runs
            }
            val previousPageLiveDataObserver: Observer<String> = mockk {
                every { onChanged("0") } just Runs
            }

            coEvery { moviesRepository.getMovies("1") } returns
                    flowOf(
                        ApiResult.Success(moviePage)
                    )
            loadingLiveDataObserver = mockk {
                every { onChanged(false) } just Runs
            }
            viewModel.getItems("1")
            viewModel.actualItemListLiveData.observeForever(actualMovieListLiveDataObserver)
            viewModel.loadingLiveData.observeForever(loadingLiveDataObserver)
            viewModel.nextPageLiveData.observeForever(nextPageLiveDataObserver)
            viewModel.previousPageLiveData.observeForever(previousPageLiveDataObserver)



            verify { nextPageLiveDataObserver.onChanged("0") }
            verify { previousPageLiveDataObserver.onChanged("0") }
            verify { loadingLiveDataObserver.onChanged(false) }
            verify { actualMovieListLiveDataObserver.onChanged(listOf( movie)) }
        }

    @Test
    fun `every call movieRepository with actual page 2 should returns success with values previous 1 and next 2`() =
        runTest(UnconfinedTestDispatcher()) {
            val nextPageLiveDataObserver: Observer<String> = mockk {
                every { onChanged("3") } just Runs
            }
            val previousPageLiveDataObserver: Observer<String> = mockk {
                every { onChanged("1") } just Runs
            }
            coEvery { moviesRepository.getMovies("2") } returns
                    flowOf(
                        ApiResult.Success(moviePage.copy(nextPage = "3", previousPage = "1"))
                    )

            viewModel.getItems("2")
            loadingLiveDataObserver = mockk {
                every { onChanged(false) } just Runs
            }
            viewModel.actualItemListLiveData.observeForever(actualMovieListLiveDataObserver)
            viewModel.loadingLiveData.observeForever(loadingLiveDataObserver)
            viewModel.nextPageLiveData.observeForever(nextPageLiveDataObserver)
            viewModel.previousPageLiveData.observeForever(previousPageLiveDataObserver)

            verify { loadingLiveDataObserver.onChanged(false) }
            verify { actualMovieListLiveDataObserver.onChanged(listOf( movie)) }
        }

    @Test
    fun `every call movieRepository should returns loading true`() =
        runTest(UnconfinedTestDispatcher()) {
            coEvery { moviesRepository.getMovies("1") } returns
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
