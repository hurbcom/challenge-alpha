package br.com.hurbandroidchallenge.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import br.com.hurbandroidchallenge.commom.extension.idFromUrl
import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.commom.mapper.NullableListMapper
import br.com.hurbandroidchallenge.commom.mapper.PagedListMapper
import br.com.hurbandroidchallenge.data.local.data_source.StarWarsBookLocalDataSource
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity
import br.com.hurbandroidchallenge.data.mapper.characters.toPeople
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
    private val context: Context
) : StarWarsBookRepository {

    override fun getHomeCategories(): Flow<List<Categories>> {
        return flow {
            if (hasInternetConnection()) {
                apiCall {
                    val remoteCategories = remoteDataSource.getHomeCategories()
                    localDataSource.setHomeCategories(
                        categories = homeCategoriesDtoToEntityMapper.map(remoteCategories),
                        reset = true
                    )
                    emit(homeCategoriesEntityToCategoriesMapper.map(localDataSource.getHomeCategories()))
                }
            } else {
                emit(homeCategoriesEntityToCategoriesMapper.map(localDataSource.getHomeCategories()))
            }
        }
    }

    override fun getCharacters(url: String): Flow<PagedList<People>> {
        return flow {
            if (hasInternetConnection()) {
                apiCall {
                    val remoteCharacters = remoteDataSource.getCharacters(url)
                    localDataSource.setCharacters(
                        characters = peopleDtoToEntityMapper.map(remoteCharacters).results,
                        reset = remoteCharacters.previous == null
                    )
                    emit(
                        PagedList(
                            count = remoteCharacters.count ?: 0,
                            next = remoteCharacters.next,
                            previous = remoteCharacters.previous,
                            results = peopleEntityToPeopleMapper.map(localDataSource.getCharacters())
                        )
                    )
                }
            } else {
                val localCharacters = localDataSource.getCharacters()
                emit(
                    PagedList(
                        count = localCharacters.size,
                        next = null,
                        previous = null,
                        results = peopleEntityToPeopleMapper.map(localCharacters)
                    )
                )
            }
        }
    }

    override fun getCharacterById(url: String, fromRemote: Boolean): Flow<People> {
        return flow {
            if (fromRemote) {
                if (hasInternetConnection()) {
                    emit(remoteDataSource.getCharacterByUrl(url).toPeople())
                } else {
                    emit(localDataSource.getCharacterById(url.idFromUrl()).toPeople())
                }
            } else {
                emit(localDataSource.getCharacterById(url.idFromUrl()).toPeople())
            }
        }
    }

    override fun getFilms(url: String): Flow<PagedList<Film>> {
        return flow {
            if (hasInternetConnection()) {
                apiCall {
                    val remoteFilms = remoteDataSource.getFilms(url)
                    localDataSource.setFilms(
                        films = filmDtoToEntityMapper.map(remoteFilms).results
                    )
                    emit(
                        PagedList(
                            count = remoteFilms.count ?: 0,
                            next = remoteFilms.next,
                            previous = remoteFilms.previous,
                            results = filmEntityToPeopleMapper.map(localDataSource.getFilms())
                        )
                    )
                }
            } else {
                val localFilms = localDataSource.getFilms()
                emit(
                    PagedList(
                        count = localFilms.size,
                        next = null,
                        previous = null,
                        results = filmEntityToPeopleMapper.map(localFilms)
                    )
                )
            }
        }
    }

    override fun getFilmByUrl(url: String, fromRemote: Boolean): Flow<Film> {
        return flow {
            if (fromRemote) {
                if (hasInternetConnection()) {
                    emit(remoteDataSource.getFilmByUrl(url).toFilm())
                } else {
                    emit(localDataSource.getFilmById(url.idFromUrl()).toFilm())
                }
            } else {
                emit(localDataSource.getFilmById(url.idFromUrl()).toFilm())
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