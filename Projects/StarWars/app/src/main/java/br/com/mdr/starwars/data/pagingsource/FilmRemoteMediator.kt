package br.com.mdr.starwars.data.pagingsource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import br.com.mdr.starwars.data.local.AppDatabase
import br.com.mdr.starwars.data.remote.api.StarWarsApi
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.model.FilmRemoteKeys

const val DEFAULT_PAGE_SIZE = 1

@OptIn(ExperimentalPagingApi::class)
class FilmRemoteMediator(
    private val api: StarWarsApi,
    private val database: AppDatabase
): RemoteMediator<Int, Film>() {

    private val filmsDao = database.getFilmDao()
    private val filmsRemoteKeysDao = database.getRemoteKeysDao()

    // Checking whether cached data is out of date
    // and decide whether to trigger a remote refresh.
    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdate = filmsRemoteKeysDao.getRemoteKeys(1)?.lastUpdated ?: 0L
        val cacheTimeOut = 5 // Time in minutes to calculate when RemoteMediator should request new data from server

        val diffInMinutes = (currentTime - lastUpdate) / 1000 / 60

        // If cache time isn't expired yet, skip server refresh
        return if (diffInMinutes.toInt() <= cacheTimeOut) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }

    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Film>): MediatorResult {
        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state = state)
                    remoteKeys?.getNextIntPage()?.minus(DEFAULT_PAGE_SIZE) ?: DEFAULT_PAGE_SIZE
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state = state)
                    val prevPage = remoteKeys?.getPreviousIntPage() ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state = state)
                    val nextPage = remoteKeys?.getNextIntPage() ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response = api.getFilms(page = page)
            if (response.results.isNotEmpty()) {
                database.withTransaction {
                    //If MediatorResult is refreshing data, delete data from tables
                    if (loadType == LoadType.REFRESH) {
                        filmsDao.deleteAllFilms()
                        filmsRemoteKeysDao.deleteRemoteKeys()
                    }

                    with(response) {
                        val key = FilmRemoteKeys(
                            prevPage = previous,
                            nextPage = next,
                            lastUpdated = System.currentTimeMillis()
                        )

                        filmsRemoteKeysDao.addRemoteKey(key)
                        filmsDao.insertFilms(response.results)
                    }
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.next == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Film>
    ): FilmRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {
                filmsRemoteKeysDao.getRemoteKeys(it)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Film>
    ): FilmRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let {
            filmsRemoteKeysDao.getRemoteKeys(it.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Film>
    ): FilmRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let {
            filmsRemoteKeysDao.getRemoteKeys(it.id)
        }
    }
}