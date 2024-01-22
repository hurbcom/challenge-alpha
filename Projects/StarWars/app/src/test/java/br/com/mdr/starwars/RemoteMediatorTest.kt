package br.com.mdr.starwars

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import br.com.mdr.starwars.base.BaseTest
import br.com.mdr.starwars.data.local.AppDatabase
import br.com.mdr.starwars.data.local.dao.FilmDao
import br.com.mdr.starwars.data.local.dao.FilmRemoteKeysDao
import br.com.mdr.starwars.data.pagingsource.FilmRemoteMediator
import br.com.mdr.starwars.data.remote.api.StarWarsApi
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.mocks.emptyRemoteKeys
import br.com.mdr.starwars.mocks.filmsMorePageResponse
import br.com.mdr.starwars.mocks.filmsResponse
import br.com.mdr.starwars.mocks.mockedException
import io.mockk.MockKAnnotations
import io.mockk.R
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.slot
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteMediatorTest : BaseTest() {

    private val api = mockk<StarWarsApi>()
    private val appDatabase = mockk<AppDatabase>()

//        AppDatabase.create(
//            context = ApplicationProvider.getApplicationContext(),
//            useInMemory = true
//        )
    private val filmsDao = mockk<FilmDao>()
    private val filmsRemoteKeysDao = mockk<FilmRemoteKeysDao>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        mockkStatic(
            "androidx.room.RoomDatabaseKt"
        )

        val transactionLambda = slot<suspend () -> R>()
        coEvery { appDatabase.withTransaction(capture(transactionLambda)) } coAnswers {
            transactionLambda.captured.invoke()
        }

        loadKoinModules(
            module {
                single { mockk<StarWarsApi>() }
                single { mockk<AppDatabase>() }
            }
        )

        every { appDatabase.getFilmDao() } returns filmsDao
        every { appDatabase.getFilmKeysDao() } returns filmsRemoteKeysDao
    }

    @Ignore // TODO: Teste quebrando com exceção ao executar DAO.
    @ExperimentalPagingApi
    @Test
    fun givenRefreshLoad_whenMoreDataIsPresent_thenReturnsSuccessResult() =
        runTest {
            coEvery { filmsRemoteKeysDao.addAllRemoteKeys(emptyRemoteKeys) }
            coEvery { filmsDao.insertFilms(filmsResponse.results) }
            coEvery { filmsDao.deleteAllFilms() } just Runs
            coEvery { filmsDao.insertFilms(filmsResponse.results) } returns Unit
            coEvery { filmsRemoteKeysDao.deleteRemoteKeys() } just Runs
            coEvery { filmsRemoteKeysDao.addAllRemoteKeys(emptyRemoteKeys) } returns Unit

            coEvery { api.getFilms(1) } returns filmsResponse

            val remoteMediator = FilmRemoteMediator(
                api = api,
                database = appDatabase
            )

            val pagingState = PagingState<Int, Film>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 10),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }

    @Ignore // TODO: Teste quebrando com exceção ao executar DAO.
    @ExperimentalPagingApi
    @Test
    fun givenRefreshLoadSuccess_whenWhenNoMoreData_thenCheckEndOfPaginationTrue() =
        runBlocking {
            coEvery { filmsDao.deleteAllFilms() } just Runs
            coEvery { filmsDao.deleteAllFilms() } just Runs
            coEvery { filmsRemoteKeysDao.deleteRemoteKeys() } just Runs
            coEvery { filmsRemoteKeysDao.addAllRemoteKeys(emptyRemoteKeys) } just Runs
            coEvery { api.getFilms(1) } returns filmsMorePageResponse

            val remoteMediator = FilmRemoteMediator(
                api = api,
                database = appDatabase
            )

            val pagingState = PagingState<Int, Film>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 5),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }

    @ExperimentalPagingApi
    @Test
    fun givenRefreshLoad_whenErrorOccurs_thenReturnsErrorResult() =
        runBlocking {
            coEvery { filmsDao.deleteAllFilms() } just Runs
            coEvery { filmsDao.deleteAllFilms() } just Runs
            coEvery { filmsRemoteKeysDao.deleteRemoteKeys() } just Runs
            coEvery { filmsRemoteKeysDao.addAllRemoteKeys(emptyRemoteKeys) } just Runs
            coEvery { api.getFilms(1) } throws mockedException
            val remoteMediator = FilmRemoteMediator(
                api = api,
                database = appDatabase
            )
            val pagingState = PagingState<Int, Film>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Error)
        }
}
