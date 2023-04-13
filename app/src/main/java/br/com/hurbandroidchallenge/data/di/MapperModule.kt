package br.com.hurbandroidchallenge.data.di

import br.com.hurbandroidchallenge.data.mapper.*
import org.koin.dsl.module

val mapperModule = module {

    single { HomeCategoriesDtoToEntityMapper() }

    single { HomeCategoriesEntityToCategoriesMapper() }

    single { PeopleDtoToEntityMapper() }

    single { PeopleEntityToPeopleMapper() }

    single { FilmDtoToEntityMapper() }

    single { FilmEntityToFilmMapper() }

}