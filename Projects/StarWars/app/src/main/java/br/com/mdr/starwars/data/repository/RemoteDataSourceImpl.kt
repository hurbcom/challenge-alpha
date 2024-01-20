package br.com.mdr.starwars.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.mdr.starwars.common.Constants.DEFAULT_PAGE_SIZE
import br.com.mdr.starwars.common.Constants.DEFAULT_PAGE_SPACE_SIZE
import br.com.mdr.starwars.data.local.AppDatabase
import br.com.mdr.starwars.data.pagingsource.CharacterRemoteMediator
import br.com.mdr.starwars.data.pagingsource.FilmRemoteMediator
import br.com.mdr.starwars.data.remote.api.StarWarsApi
import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.repository.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

@OptIn(ExperimentalPagingApi::class)
class RemoteDataSourceImpl(
    private val api: StarWarsApi,
    private val dataBase: AppDatabase
) : RemoteDataSource {
    private val filmDao = dataBase.getFilmDao()
    private val charactersDao = dataBase.getCharactersDao()

    override fun getAllFilms(): Flow<PagingData<Film>> {
        val pagingSourceFactory = { filmDao.getFilms() }
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
        val pagingSourceFactory = { filmDao.getFilmsByTitle(query) }
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory
        ).flow.flowOn(Dispatchers.IO)
    }

    override fun getAllCharacters(): Flow<PagingData<Character>> {
        val pagingSourceFactory = { charactersDao.getCharacters() }
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                prefetchDistance = DEFAULT_PAGE_SPACE_SIZE
            ),
            remoteMediator = CharacterRemoteMediator(
                api = api,
                database = dataBase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.flowOn(Dispatchers.IO)
    }

    override fun searchCharacters(query: String): Flow<PagingData<Character>> {
        val pagingSourceFactory = { charactersDao.getCharactersByTitle(query) }
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                prefetchDistance = DEFAULT_PAGE_SPACE_SIZE
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.flowOn(Dispatchers.IO)
    }
}
