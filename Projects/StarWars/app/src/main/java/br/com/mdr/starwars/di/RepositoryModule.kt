package br.com.mdr.starwars.di

import br.com.mdr.starwars.data.repository.CategoryRepositoryImpl
import br.com.mdr.starwars.data.repository.CharacterRepositoryImpl
import br.com.mdr.starwars.data.repository.FavoritesRepositoryImpl
import br.com.mdr.starwars.data.repository.FilmRepositoryImpl
import br.com.mdr.starwars.data.repository.LastSeenRepositoryImpl
import br.com.mdr.starwars.domain.repository.CategoryRepository
import br.com.mdr.starwars.domain.repository.CharacterRepository
import br.com.mdr.starwars.domain.repository.FavoritesRepository
import br.com.mdr.starwars.domain.repository.FilmRepository
import br.com.mdr.starwars.domain.repository.LastSeenRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<FilmRepository> { FilmRepositoryImpl(get(), get()) }
    single<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }
    single<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
    single<LastSeenRepository> { LastSeenRepositoryImpl(get()) }
}
