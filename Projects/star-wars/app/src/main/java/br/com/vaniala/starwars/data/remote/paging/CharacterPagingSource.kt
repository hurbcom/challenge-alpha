package br.com.vaniala.starwars.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.withTransaction
import br.com.vaniala.starwars.core.StatusConnectivity
import br.com.vaniala.starwars.data.local.database.StarWarsDatabase
import br.com.vaniala.starwars.domain.model.People
import br.com.vaniala.starwars.domain.repository.CharacterRepository

private const val STARTING_PAGE_INDEX = 1

class CharacterPagingSource(
    private val query: String,
    private val repository: CharacterRepository,
    private val starWarsDatabase: StarWarsDatabase,
    private val statusConnectivity: StatusConnectivity,
) : PagingSource<Int, People>() {

    override fun getRefreshKey(state: PagingState<Int, People>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {
        return try {
            var isDataUpdate = false
            val isNotConnected = !statusConnectivity.isConnected()

            starWarsDatabase.withTransaction {
                isDataUpdate = repository.isUpdate()
            }

            if (isDataUpdate || isNotConnected) {
                pageLocal()
            } else {
                val nextPage: Int = params.key ?: STARTING_PAGE_INDEX
                val response = repository.getCharacters(nextPage)
                val characters = response.results

                val nextPageNumber = if (response.next == null) null else nextPage + 1
                val isUpdate = nextPageNumber == null

                characters.forEach { character ->
                    character.timestamp = System.currentTimeMillis()
                    character.isUpdate = isUpdate
                }

                starWarsDatabase.withTransaction {
                    repository.insertAll(characters)
                }

                LoadResult.Page(
                    data = response.results,
                    prevKey = null,
                    nextKey = nextPageNumber,
                )
            }
        } catch (ignore: Exception) {
            LoadResult.Error(ignore)
        }
    }

    private suspend fun pageLocal(): LoadResult.Page<Int, People> {
        var characterByName = emptyList<People>()

        starWarsDatabase.withTransaction {
            characterByName = repository.charactersByName(query)
        }

        return LoadResult.Page(
            data = characterByName,
            prevKey = null,
            nextKey = null,
        )
    }
}
