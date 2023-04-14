package br.com.hurbandroidchallenge.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import br.com.hurbandroidchallenge.commom.extension.idFromUrl
import br.com.hurbandroidchallenge.commom.extension.pagedListOf
import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.commom.mapper.NullableListMapper
import br.com.hurbandroidchallenge.commom.mapper.PagedListMapper
import br.com.hurbandroidchallenge.data.local.data_source.StarWarsBookLocalDataSource
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity
import br.com.hurbandroidchallenge.data.local.preferences.PreferencesWrapper
import br.com.hurbandroidchallenge.data.mapper.characters.toEntity
import br.com.hurbandroidchallenge.data.mapper.characters.toPeople
import br.com.hurbandroidchallenge.data.mapper.films.toEntity
import br.com.hurbandroidchallenge.data.mapper.films.toFilm
import br.com.hurbandroidchallenge.data.remote.data_sources.StarWarsBookRemoteDataSource
import br.com.hurbandroidchallenge.data.remote.model.FilmDto
import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesDto
import br.com.hurbandroidchallenge.data.remote.model.PeopleDto
import br.com.hurbandroidchallenge.data.remote.util.apiCall
import br.com.hurbandroidchallenge.domain.model.Categories
import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.model.base.PagedList
import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class StarWarsBookRepositoryImpl(
    private val remoteDataSource: StarWarsBookRemoteDataSource,
    private val localDataSource: StarWarsBookLocalDataSource,
    private val homeCategoriesEntityToCategoriesMapper: NullableListMapper<HomeCategoriesEntity, Categories>,
    private val homeCategoriesDtoToEntityMapper: Mapper<HomeCategoriesDto, List<HomeCategoriesEntity>>,
    private val peopleDtoToEntityMapper: PagedListMapper<PeopleDto, PeopleEntity>,
    private val peopleEntityToPeopleMapper: NullableListMapper<PeopleEntity, People>,
    private val filmDtoToEntityMapper: PagedListMapper<FilmDto, FilmEntity>,
    private val filmEntityToPeopleMapper: NullableListMapper<FilmEntity, Film>,
    private val context: Context,
) : StarWarsBookRepository {

    private val preferences = PreferencesWrapper.getInstance()

    private suspend fun updateLocalCategories() {
        val remoteCategories = remoteDataSource.getHomeCategories()
        localDataSource.updateHomeCategories(
            categories = homeCategoriesDtoToEntityMapper.map(remoteCategories)
        )
    }

    private suspend fun getLocalCategories() =
        homeCategoriesEntityToCategoriesMapper.map(localDataSource.getHomeCategories())

    override fun getHomeCategories(): Flow<List<Categories>> {
        return flow {
            if (preferences.isCategoriesUpToDate()) {
                emit(getLocalCategories())
            } else if (hasInternetConnection()) {
                apiCall {
                    updateLocalCategories()
                    emit(getLocalCategories())
                }
            } else {
                emit(getLocalCategories())
            }
        }
    }


    private suspend fun getLocalCharacters() =
        peopleEntityToPeopleMapper.map(localDataSource.getCharacters())

    private suspend fun getLocalCharacterByUrl(url: String) =
        localDataSource.getCharacterById(url.idFromUrl()).toPeople()

    override fun getCharacters(url: String): Flow<PagedList<People>> {
        return flow {
            if (preferences.isCharactersUpToDate()) {
                emit(pagedListOf(getLocalCharacters()))
            } else if (hasInternetConnection()) {
                apiCall {
                    val remoteCharacters = remoteDataSource.getCharacters(url)
                    localDataSource.setCharacters(peopleDtoToEntityMapper.map(remoteCharacters).results)
                    if (remoteCharacters.next == null)
                        preferences.charactersIsUpToDate()
                    emit(
                        PagedList(
                            next = remoteCharacters.next,
                            previous = remoteCharacters.previous,
                            results = getLocalCharacters()
                        )
                    )
                }
            } else {
                emit(pagedListOf(getLocalCharacters()))
            }
        }
    }

    override fun getCharacterById(url: String): Flow<People> {
        return flow {
            if (localDataSource.containsCharacter(url.idFromUrl())) {
                emit(getLocalCharacterByUrl(url))
            } else if (hasInternetConnection()) {
                val remoteCharacter = remoteDataSource.getCharacterByUrl(url)
                localDataSource.setCharacters(listOf(remoteCharacter.toEntity()))
                emit(remoteCharacter.toPeople())
            } else {
                getLocalCharacterByUrl(url)
            }
        }
    }

    private suspend fun getLocalFilms() = filmEntityToPeopleMapper.map(localDataSource.getFilms())

    private suspend fun getLocalFilmByUrl(url: String) = localDataSource.getFilmById(url.idFromUrl()).toFilm()

    override fun getFilms(url: String): Flow<PagedList<Film>> {
        return flow {
            if (preferences.isFilmsUpToDate()) {
                emit(pagedListOf(getLocalFilms()))
            } else if (hasInternetConnection()) {
                apiCall {
                    val remoteFilms = remoteDataSource.getFilms(url)
                    localDataSource.setFilms(filmDtoToEntityMapper.map(remoteFilms).results)
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
                }
            } else {
                emit(pagedListOf(getLocalFilms()))
            }
        }
    }

    override fun getFilmByUrl(url: String): Flow<Film> {
        return flow {
            if (localDataSource.containsFilm(url.idFromUrl())) {
                emit(getLocalFilmByUrl(url))
            } else if (hasInternetConnection()) {
                val remoteFilm = remoteDataSource.getFilmByUrl(url)
                localDataSource.setFilms(listOf(remoteFilm.toEntity()))
                emit(remoteFilm.toFilm())
            } else {
                getLocalFilmByUrl(url)
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}