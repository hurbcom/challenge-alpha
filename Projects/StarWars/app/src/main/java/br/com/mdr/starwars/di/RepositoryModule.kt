package br.com.mdr.starwars.di

import br.com.mdr.starwars.data.repository.CategoryRepositoryImpl
import br.com.mdr.starwars.data.repository.FilmRepositoryImpl
import br.com.mdr.starwars.domain.repository.CategoryRepository
import br.com.mdr.starwars.domain.repository.FilmRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<FilmRepository> { FilmRepositoryImpl(get(), get()) }
}