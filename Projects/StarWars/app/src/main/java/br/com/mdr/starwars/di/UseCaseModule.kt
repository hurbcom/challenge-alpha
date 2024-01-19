package br.com.mdr.starwars.di

import br.com.mdr.starwars.domain.usecase.CategoryUseCase
import br.com.mdr.starwars.domain.usecase.CharacterDetailUseCase
import br.com.mdr.starwars.domain.usecase.CharacterUseCase
import br.com.mdr.starwars.domain.usecase.FavoritesUseCase
import br.com.mdr.starwars.domain.usecase.FilmDetailUseCase
import br.com.mdr.starwars.domain.usecase.FilmUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { CategoryUseCase(get()) }
    single { FilmUseCase(get()) }
    single { FilmDetailUseCase(get()) }
    single { CharacterUseCase(get()) }
    single { CharacterDetailUseCase(get()) }
    single { FavoritesUseCase(get()) }
}