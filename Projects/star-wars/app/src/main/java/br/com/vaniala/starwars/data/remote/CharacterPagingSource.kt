package br.com.vaniala.starwars.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.vaniala.starwars.data.remote.service.ApiService
import br.com.vaniala.starwars.domain.model.People

private const val STARTING_PAGE_INDEX = 1

class CharacterPagingSource(
    private val service: ApiService,
) : PagingSource<Int, People>() {

    override fun getRefreshKey(state: PagingState<Int, People>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {
        return try {
            val nextPage: Int = params.key ?: STARTING_PAGE_INDEX
            val response = service.getPeople(nextPage)

            val prevPageNumber = if (nextPage == STARTING_PAGE_INDEX) null else nextPage - 1
            val nextPageNumber = if (response.next == null) null else nextPage + 1

            LoadResult.Page(
                data = response.results,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber,
            )
        } catch (ignore: Exception) {
            LoadResult.Error(ignore)
        }
    }
}
