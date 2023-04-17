package br.com.hurbandroidchallenge.data.repository

import br.com.hurbandroidchallenge.commom.extension.idFromUrl
import br.com.hurbandroidchallenge.commom.extension.pagedListOf
import br.com.hurbandroidchallenge.commom.mapper.NullableListMapper
import br.com.hurbandroidchallenge.commom.mapper.PagedListMapper
import br.com.hurbandroidchallenge.data.local.data_source.FilmsLocalDataSource
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.preferences.PreferencesWrapper
import br.com.hurbandroidchallenge.data.mapper.films.toEntity
import br.com.hurbandroidchallenge.data.mapper.films.toFilm
import br.com.hurbandroidchallenge.data.remote.data_sources.StarWarsBookRemoteDataSource
import br.com.hurbandroidchallenge.data.remote.model.FilmDto
import br.com.hurbandroidchallenge.data.remote.util.NetworkManager
import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.PagedList
import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilmsRepository(
    private val remoteDataSource: StarWarsBookRemoteDataSource,
    private val localDataSource: FilmsLocalDataSource,
    private val filmDtoToEntityMapper: PagedListMapper<FilmDto, FilmEntity>,
    private val filmEntityToPeopleMapper: NullableListMapper<FilmEntity, Film>,
    private val networkManager: NetworkManager,
) : StarWarsBookRepository<Film> {

    private val preferences = PreferencesWrapper.getInstance()

    private suspend fun getLocalFilms() =
        filmEntityToPeopleMapper.map(localDataSource.getEntities())

    private suspend fun getLocalFilmByUrl(url: String) =
        localDataSource.getEntityById(url.idFromUrl()).toFilm()

    override fun getItemList(url: String, clearLocalDatasource: Boolean): Flow<PagedList<Film>> {
        return flow {
            if (clearLocalDatasource) localDataSource.clearEntities()
            if (preferences.isFilmsUpToDate()) {
                emit(pagedListOf(getLocalFilms()))
            } else if (networkManager.hasInternetConnection()) {
                val remoteFilms = remoteDataSource.getFilms(url)
                localDataSource.insertEntities(filmDtoToEntityMapper.map(remoteFilms).results)
                if (remoteFilms.next == null) {
                    preferences.filmsIsUpToDate()
                }
                emit(
                    PagedList(
                        next = remoteFilms.next,
                        previous = remoteFilms.previous,
                        results = getLocalFilms()
                    )
                )
            } else {
                emit(pagedListOf(getLocalFilms()))
            }
        }
    }

    override fun getItemByUrl(url: String): Flow<Film> {
        return flow {
            if (localDataSource.containsEntity(url.idFromUrl())) {
                emit(getLocalFilmByUrl(url))
            } else if (networkManager.hasInternetConnection()) {
                val remoteFilm = remoteDataSource.getFilmByUrl(url)
                localDataSource.insertEntities(listOf(remoteFilm.toEntity()))
                val localFilm = getLocalFilmByUrl(remoteFilm.url.orEmpty())
                emit(localFilm)
            } else {
                getLocalFilmByUrl(url)
            }
        }
    }

    override fun getFavoriteItems(): Flow<List<Film>> {
        return flow { emit(filmEntityToPeopleMapper.map(localDataSource.getFavoriteEntities())) }
    }

    override fun getLastSeenItems(): Flow<List<Film>> {
        return flow { emit(filmEntityToPeopleMapper.map(localDataSource.getLastSeenEntities())) }
    }

    override fun updateItem(item: Film): Flow<Film> {
        return flow { emit(localDataSource.updateEntity(item.toEntity()).toFilm()) }
    }

}