package br.com.mdr.starwars.data.pagingsource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import br.com.mdr.starwars.common.Constants.DEFAULT_DB_CACHE_TIME
import br.com.mdr.starwars.data.local.AppDatabase
import br.com.mdr.starwars.data.remote.api.StarWarsApi
import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.domain.model.CharacterRemoteKeys

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val api: StarWarsApi,
    private val database: AppDatabase
): RemoteMediator<Int, Character>() {

    private val charactersDao = database.getCharactersDao()
    private val charactersRemoteKeysDao = database.getCharacterKeysDao()

    // Checking whether cached data is out of date
    // and decide whether to trigger a remote refresh.
    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdate = charactersRemoteKeysDao.getRemoteKey()?.lastUpdated ?: 0L

        val diffInMinutes = (currentTime - lastUpdate) / 1000 / 60

        // DEFAULT_DB_CACHE_TIME = Time in minutes to calculate when RemoteMediator should request new data from server
        // If cache time isn't expired yet, skip server refresh
        return if (diffInMinutes.toInt() <= DEFAULT_DB_CACHE_TIME) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Character>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state = state)
                    remoteKeys?.getNextIntPage()?.minus(DEFAULT_PAGE_SIZE) ?: DEFAULT_PAGE_SIZE
                }
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state = state)
                    val nextPage = remoteKeys?.getNextIntPage() ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response = api.getCharacters(page = page)
            if (response.results.isNotEmpty()) {

                database.withTransaction {

                    if (loadType == LoadType.REFRESH) {
                        charactersDao.deleteAllCharacters()
                        charactersRemoteKeysDao.deleteRemoteKeys()
                    }

                    with(response) {
                        val keys = results.map { character ->
                            CharacterRemoteKeys(
                                name = character.name,
                                prevPage = previous,
                                nextPage = next,
                                lastUpdated = System.currentTimeMillis()
                            )
                        }

                        charactersRemoteKeysDao.addAllRemoteKeys(keys)
                        charactersDao.insertCharacters(response.results)
                    }
                }
            }

            MediatorResult.Success(endOfPaginationReached = response.next == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Character>
    ): CharacterRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.name?.let {
                charactersRemoteKeysDao.getRemoteKeys(it)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Character>
    ): CharacterRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let {
            charactersRemoteKeysDao.getRemoteKeys(it.name)
        }
    }
}