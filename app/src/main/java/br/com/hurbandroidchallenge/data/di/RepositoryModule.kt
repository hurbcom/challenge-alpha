package br.com.hurbandroidchallenge.data.di

import br.com.hurbandroidchallenge.commom.mapper.NullableListMapperImpl
import br.com.hurbandroidchallenge.commom.mapper.PagedListMapperImpl
import br.com.hurbandroidchallenge.data.mapper.*
import br.com.hurbandroidchallenge.data.repository.StarWarsBookRepositoryImpl
import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<StarWarsBookRepository> {
        StarWarsBookRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get(),
            filmDtoToEntityMapper = PagedListMapperImpl(
                listMapper = NullableListMapperImpl(
                    mapper = get<FilmDtoToEntityMapper>()
                )
            ),
            filmEntityToPeopleMapper = NullableListMapperImpl(
                mapper = get<FilmEntityToFilmMapper>()
            ),
            homeCategoriesDtoToEntityMapper = get<HomeCategoriesDtoToEntityMapper>(),
            peopleDtoToEntityMapper = PagedListMapperImpl(
                listMapper = NullableListMapperImpl(
                    mapper = get<PeopleDtoToEntityMapper>()
                )
            ),
            peopleEntityToPeopleMapper = NullableListMapperImpl(
                mapper = get<PeopleEntityToPeopleMapper>()
            ),
            homeCategoriesEntityToCategoriesMapper = NullableListMapperImpl(
                mapper = get<HomeCategoriesEntityToCategoriesMapper>()
            )
        )
    }

}