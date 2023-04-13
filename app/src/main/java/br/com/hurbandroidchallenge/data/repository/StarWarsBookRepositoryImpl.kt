package br.com.hurbandroidchallenge.data.repository

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.commom.mapper.NullableListMapper
import br.com.hurbandroidchallenge.commom.mapper.PagedListMapper
import br.com.hurbandroidchallenge.data.local.data_source.StarWarsBookLocalDataSource
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity
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
) : StarWarsBookRepository {

    override fun getHomeCategories(): Flow<List<Categories>> {
        return flow {
            apiCall {
                val remoteCategories = remoteDataSource.getHomeCategories()
                localDataSource.setHomeCategories(
                    categories = homeCategoriesDtoToEntityMapper.map(remoteCategories),
                    reset = true
                )
                emit(homeCategoriesEntityToCategoriesMapper.map(localDataSource.getHomeCategories()))
            }
        }
    }

    override fun getCharacters(url: String): Flow<PagedList<People>> {
        return flow {
            apiCall {
                val remoteCharacters = remoteDataSource.getCharacters(url)
                localDataSource.setCharacters(
                    characters = peopleDtoToEntityMapper.map(remoteCharacters).results,
                    reset = url.contains("page")
                )
                emit(
                    PagedList(
                        count = remoteCharacters.count ?: 0,
                        next = remoteCharacters.next,
                        previous = remoteCharacters.previous,
                        results = peopleEntityToPeopleMapper.map(
                            localDataSource.getCharacters()
                        )
                    )
                )
            }
        }
    }

    override fun getFilms(url: String): Flow<PagedList<Film>> {
        return flow {
            apiCall {
                val remoteFilms = remoteDataSource.getFilms(url)
                localDataSource.setFilms(
                    films = filmDtoToEntityMapper.map(remoteFilms).results,
                    reset = url.contains("page")
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
        }
    }
}