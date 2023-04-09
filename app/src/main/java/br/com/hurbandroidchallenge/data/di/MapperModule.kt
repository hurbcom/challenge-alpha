package br.com.hurbandroidchallenge.data.di

import br.com.hurbandroidchallenge.data.mapper.HomeCategoriesResponseToEntityMapper
import org.koin.dsl.module

val mapperModule = module {

    single { HomeCategoriesResponseToEntityMapper() }

}