package br.com.mdr.starwars.viewmodel

import br.com.mdr.starwars.base.BaseTest
import br.com.mdr.starwars.data.local.AppDatabase
import br.com.mdr.starwars.di.viewModelModule
import br.com.mdr.starwars.domain.model.LastSeen
import br.com.mdr.starwars.domain.model.PageState
import br.com.mdr.starwars.domain.repository.LastSeenRepository
import br.com.mdr.starwars.domain.usecase.LastSeenUseCase
import br.com.mdr.starwars.mocks.emitLastSeen
import br.com.mdr.starwars.mocks.emptyLastSeen
import br.com.mdr.starwars.mocks.getLastSeen
import br.com.mdr.starwars.mocks.lastSeen
import br.com.mdr.starwars.ui.presentation.lastSeen.LastSeenViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class LastSeenViewModelTest: BaseTest() {

    private val viewModel: LastSeenViewModel by inject()
    private val useCase: LastSeenUseCase by inject()
    private val repository: LastSeenRepository by inject()

    @Before
    fun setup() {
        loadKoinModules(
            module {
                single { mockk<AppDatabase>() }
                single { mockk<LastSeenRepository>() }
                single { mockk<LastSeenUseCase>() }
            } + viewModelModule
        )
    }

    @Test
    fun givenSuccess_whenLoadingLastSeen_thenCheckSuccessResult() {
        runTest {
            var expectedState: PageState<List<LastSeen>> = PageState.Loading

            assertEquals(expectedState, viewModel.lastSeen.value)

            expectedState = viewModel.lastSeen.value
            val films = viewModel.films
            val characters = viewModel.characters

            coEvery { repository.getLastSeen() } returns flow{getLastSeen()}
            coEvery { useCase.getLastSeen() } returns emitLastSeen(lastSeen)

            viewModel.getLastSeen()

            assertEquals(expectedState, viewModel.lastSeen.value)
            assertEquals(films, viewModel.films)
            assertEquals(characters, viewModel.characters)
        }
    }

    @Test
    fun givenSuccess_whenLoadingLastSeen_thenCheckEmptyResult() {
        runTest {
            var expectedState: PageState<List<LastSeen>> = PageState.Loading

            assertEquals(expectedState, viewModel.lastSeen.value)

            expectedState = viewModel.lastSeen.value
            val films = viewModel.films
            val characters = viewModel.characters

            coEvery { repository.getLastSeen() } returns flow{ emptyLastSeen }

            viewModel.getLastSeen()

            assertEquals(expectedState, viewModel.lastSeen.value)
            assert(films.isEmpty())
            assert(characters.isEmpty())
        }
    }

}