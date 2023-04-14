package br.com.vaniala.starwars.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.withTransaction
import br.com.vaniala.starwars.core.StatusConnectivity
import br.com.vaniala.starwars.data.local.database.StarWarsDatabase
import br.com.vaniala.starwars.data.local.datasource.LocalDataSource
import br.com.vaniala.starwars.domain.model.People
import br.com.vaniala.starwars.domain.repository.CharacterRepository

private const val STARTING_PAGE_INDEX = 1

class CharacterPagingSource(
    private val query: String,
    private val repository: CharacterRepository,
    private val charactersDataSource: LocalDataSource.Characters,
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
        var total = 0
        starWarsDatabase.withTransaction {
            total = charactersDataSource.getCount()
        }

        return try {
            if (statusConnectivity.isConnected() && query.isEmpty()) {
                val nextPage: Int = params.key ?: STARTING_PAGE_INDEX
                val response = repository.getCharacters(nextPage)
                val characters = response.results

                if (response.count != total) {
                    val nextPageNumber = if (response.next == null) null else nextPage + 1

                    characters.forEach { character ->
                        character.timestamp = System.currentTimeMillis()
                    }
                    charactersDataSource.insertAll(characters)
                    LoadResult.Page(
                        data = response.results,
                        prevKey = null,
                        nextKey = nextPageNumber,
                    )
                } else {
                    page()
                }
            } else {
                page()
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun page(): LoadResult.Page<Int, People> {
        var characterByName = emptyList<People>()
        starWarsDatabase.withTransaction {
            characterByName = charactersDataSource.characterByName(query)
        }
        return LoadResult.Page(
            data = characterByName,
            prevKey = null,
            nextKey = null,
        )
    }
}
