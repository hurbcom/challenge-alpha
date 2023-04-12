package br.com.hurbandroidchallenge.data.di

import br.com.hurbandroidchallenge.commom.mapper.NullableListMapperImpl
import br.com.hurbandroidchallenge.commom.mapper.PagedListMapperImpl
import br.com.hurbandroidchallenge.data.mapper.FilmResponseToEntityMapper
import br.com.hurbandroidchallenge.data.mapper.HomeCategoriesResponseToEntityMapper
import br.com.hurbandroidchallenge.data.mapper.PeopleResponseToEntityMapper
import br.com.hurbandroidchallenge.data.remote.data_sources.StarWarsBookRemoteDataSourceImpl
import br.com.hurbandroidchallenge.data.repository.StarWarsBookRepositoryImpl
import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<StarWarsBookRepository> {
        StarWarsBookRepositoryImpl(
            remoteDataSource = StarWarsBookRemoteDataSourceImpl(get()),
            homeCategoriesResponseToEntityMapper = get<HomeCategoriesResponseToEntityMapper>(),
            peopleResponseToEntityMapper = PagedListMapperImpl(
                listMapper = NullableListMapperImpl(
                    mapper = get<PeopleResponseToEntityMapper>()
                )
            ),
            filmResponseToEntityMapper = PagedListMapperImpl(
                listMapper = NullableListMapperImpl(
                    mapper = get<FilmResponseToEntityMapper>()
                )
            )
        )
    }

}