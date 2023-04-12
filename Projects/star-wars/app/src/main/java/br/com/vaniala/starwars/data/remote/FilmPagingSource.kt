package br.com.vaniala.starwars.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.vaniala.starwars.data.remote.service.ApiService
import br.com.vaniala.starwars.domain.model.Films

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */
private const val STARTING_PAGE_INDEX = 1

class FilmPagingSource(
    private val service: ApiService,
) : PagingSource<Int, Films>() {

    override fun getRefreshKey(state: PagingState<Int, Films>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Films> {
        return try {
            val nextPage: Int = params.key ?: STARTING_PAGE_INDEX
            val response = service.getFilms(nextPage)

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
