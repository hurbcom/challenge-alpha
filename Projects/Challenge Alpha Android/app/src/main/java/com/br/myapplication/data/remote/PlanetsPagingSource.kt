package com.br.myapplication.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.br.myapplication.data.dao.PlanetsDao
import com.br.myapplication.data.model.Planet
import com.br.myapplication.data.repository.planet.IPlanetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlanetsPagingSource(
    private val repository: IPlanetRepository,
    private val planetsDao: PlanetsDao
) : PagingSource<Int, Planet>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Planet> {
        return try {

            val page = params.key ?: 1

            withContext(Dispatchers.IO) {
                val planetsFromApi = repository.getPlanetList(page.toString())

                planetsFromApi.forEach { planetsDao.insertPlanets(it) }

                val planetsFromDb = planetsDao.getAllPlanetsPaging((page - 1) * params.loadSize, params.loadSize)

                LoadResult.Page(
                    data = planetsFromDb,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = page + 1
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Planet>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}