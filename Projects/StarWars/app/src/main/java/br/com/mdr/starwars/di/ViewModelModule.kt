package br.com.mdr.starwars.di

import br.com.mdr.starwars.ui.presentation.categories.CategoriesViewModel
import br.com.mdr.starwars.ui.presentation.characters.CharactersViewModel
import br.com.mdr.starwars.ui.presentation.characters.detail.CharacterDetailViewModel
import br.com.mdr.starwars.ui.presentation.favorites.FavoritesViewModel
import br.com.mdr.starwars.ui.presentation.films.FilmsViewModel
import br.com.mdr.starwars.ui.presentation.films.detail.FilmDetailViewModel
import br.com.mdr.starwars.ui.presentation.lastSeen.LastSeenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CategoriesViewModel(get()) }
    viewModel { FilmsViewModel(get()) }
    viewModel { FilmDetailViewModel(get(), get()) }
    viewModel { CharactersViewModel(get()) }
    viewModel { CharacterDetailViewModel(get(), get()) }
    viewModel { FavoritesViewModel(get()) }
    viewModel { LastSeenViewModel(get()) }
}