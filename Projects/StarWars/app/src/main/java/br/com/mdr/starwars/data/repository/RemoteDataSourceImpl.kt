package br.com.mdr.starwars.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.mdr.starwars.common.Constants.DEFAULT_PAGE_SIZE
import br.com.mdr.starwars.data.local.AppDatabase
import br.com.mdr.starwars.data.pagingsource.FilmRemoteMediator
import br.com.mdr.starwars.data.remote.api.StarWarsApi
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.repository.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSourceImpl(
    private val api: StarWarsApi,
    private val dataBase: AppDatabase
): RemoteDataSource {
    private val dao = dataBase.getFilmDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllFilms(): Flow<PagingData<Film>> {
        val pagingSourceFactory = { dao.getFilms() }
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
            remoteMediator = FilmRemoteMediator(
                api = api,
                database = dataBase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.flowOn(Dispatchers.IO)
    }

    override fun searchFilms(query: String): Flow<PagingData<Film>> {
        val pagingSourceFactory = { dao.getFilmsByTitle(query) }
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory
        ).flow.flowOn(Dispatchers.IO)
    }

}