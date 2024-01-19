package br.com.mdr.starwars.di

import br.com.mdr.starwars.domain.usecase.CategoryUseCase
import br.com.mdr.starwars.domain.usecase.CharacterUseCase
import br.com.mdr.starwars.domain.usecase.FilmUseCase
import br.com.mdr.starwars.presentation.characters.detail.CharacterDetailUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { CategoryUseCase(get()) }
    single { FilmUseCase(get()) }
    single { CharacterDetailUseCase(get()) }
    single { CharacterUseCase(get()) }
}