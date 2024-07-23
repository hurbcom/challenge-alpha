package com.example.hurb_challenge.app.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hurb_challenge.app.core.extractNumbers
import com.example.hurb_challenge.app.domain.model.Character
import com.example.hurb_challenge.app.domain.repository.CharactersRepository
import com.example.hurb_challenge.app.domain.toDomain

class CharactersPagingSource(
    private val repository: CharactersRepository
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>) =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>) = try {
        val currentPage = params.key ?: 1
        var nextPageNumber: Int? = null
        var data: List<Character> = emptyList()
        repository.getCharactersByPage(page = currentPage).collect { response ->
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
