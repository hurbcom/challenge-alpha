package com.br.myapplication.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.br.myapplication.data.model.Film
import com.br.myapplication.data.model.Specie
import com.br.myapplication.data.repository.film.IFilmRepository
import com.br.myapplication.data.repository.specie.ISpeciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SpeciesPagingSource(
    private val repository: ISpeciesRepository
) : PagingSource<Int, Specie>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Specie> {
        return try {

            val page = params.key ?: 1

            withContext(Dispatchers.IO) {
                val response = repository.geSpecieList(page.toString())

                LoadResult.Page(
                    data = response,
                    prevKey = if (page > 1) page - 1 else null,
                    nextKey = if (response.isNotEmpty()) page + 1 else null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Specie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}