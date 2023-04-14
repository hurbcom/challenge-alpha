package br.com.vaniala.starwars.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.withTransaction
import br.com.vaniala.starwars.core.StatusConnectivity
import br.com.vaniala.starwars.data.local.database.StarWarsDatabase
import br.com.vaniala.starwars.data.local.datasource.LocalDataSource
import br.com.vaniala.starwars.domain.model.Film
import br.com.vaniala.starwars.domain.repository.FilmRepository

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */

private const val STARTING_PAGE_INDEX = 1
class FilmPagingSource(
    private val query: String,
    private val repository: FilmRepository,
    private val filmsDataSource: LocalDataSource.Films,
    private val starWarsDatabase: StarWarsDatabase,
    private val statusConnectivity: StatusConnectivity,
) : PagingSource<Int, Film>() {

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        var total = 0
        starWarsDatabase.withTransaction {
            total = filmsDataSource.getCount()
        }
        return try {
            if (statusConnectivity.isConnected() && query.isEmpty()) {
                val nextPage: Int = params.key ?: STARTING_PAGE_INDEX
                val response = repository.getFilms(nextPage)
                val films = response.results
                if (response.count != total) {
                    val nextPageNumber = if (response.next == null) null else nextPage + 1

                    films.forEach { film ->
                        film.timestamp = System.currentTimeMillis()
                    }
                    filmsDataSource.insertAll(films)

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

    private suspend fun page(): LoadResult.Page<Int, Film> {
        var filmsByName = emptyList<Film>()
        starWarsDatabase.withTransaction {
            filmsByName = filmsDataSource.filmsByTitle(query)
        }
        return LoadResult.Page(
            data = filmsByName,
            prevKey = null,
            nextKey = null,
        )
    }
}
