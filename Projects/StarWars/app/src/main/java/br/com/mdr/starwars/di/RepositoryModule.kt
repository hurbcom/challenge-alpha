package br.com.mdr.starwars.di

import br.com.mdr.starwars.data.repository.CategoryRepositoryImpl
import br.com.mdr.starwars.domain.repository.CategoryRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
}