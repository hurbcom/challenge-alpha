package br.com.hurbandroidchallenge.data.di

import br.com.hurbandroidchallenge.data.mapper.HomeCategoriesResponseToEntityMapper
import br.com.hurbandroidchallenge.data.mapper.PeopleResponseToEntityMapper
import org.koin.dsl.module

val mapperModule = module {

    single { HomeCategoriesResponseToEntityMapper() }

    single { PeopleResponseToEntityMapper() }

}