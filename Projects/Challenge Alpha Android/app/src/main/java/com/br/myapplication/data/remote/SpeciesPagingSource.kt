package com.br.myapplication.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.br.myapplication.data.dao.SpeciesDao
import com.br.myapplication.data.model.Specie
import com.br.myapplication.data.repository.specie.ISpeciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SpeciesPagingSource(
    private val repository: ISpeciesRepository,
    private val speciesDao: SpeciesDao
) : PagingSource<Int, Specie>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Specie> {
        return try {

            val page = params.key ?: 1

            withContext(Dispatchers.IO) {
                val speciesFromApi = repository.geSpecieList(page.toString())

                speciesFromApi.forEach { speciesDao.insertSpecies(it) }

                val speciesFromDb = speciesDao.getAllSpeciesPaging((page - 1) * params.loadSize, params.loadSize)

                LoadResult.Page(
                    data = speciesFromDb,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = page + 1
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