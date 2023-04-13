package br.com.hurbandroidchallenge.domain.di

import br.com.hurbandroidchallenge.domain.use_case.*
import org.koin.dsl.module

val useCaseModule = module {

    single { GetHomeCategoriesUseCase(get()) }

    single { GetCharactersUseCase(get()) }

    single { GetFilmsUseCase(get()) }

}