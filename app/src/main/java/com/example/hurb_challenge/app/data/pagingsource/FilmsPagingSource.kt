package com.example.hurb_challenge.app.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hurb_challenge.app.core.extractNumbers
import com.example.hurb_challenge.app.domain.model.Film
import com.example.hurb_challenge.app.domain.repository.FilmsRepository
import com.example.hurb_challenge.app.domain.toDomain

class FilmsPagingSource(
    private val repository: FilmsRepository
) : PagingSource<Int, Film>() {

    override fun getRefreshKey(state: PagingState<Int, Film>) =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>) = try {
        val currentPage = params.key ?: 1
        var nextPageNumber: Int? = null
        var data: List<Film> = emptyList()
        repository.getFilms(page = currentPage).collect { response ->
            nextPageNumber = if (response.next == null) null else currentPage + 1
            data = response.results.map { dto ->
                val id = dto.url.extractNumbers()
                dto.toDomain(id)
            }
        }
        LoadResult.Page(
            data = data,
            prevKey = if (currentPage == 1) null else currentPage - 1,
            nextKey = nextPageNumber
        )

    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}
