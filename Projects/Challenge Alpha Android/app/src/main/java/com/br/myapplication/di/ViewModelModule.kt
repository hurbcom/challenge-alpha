package com.br.myapplication.di

import com.br.myapplication.ui.favorites.FavoritesViewModel
import com.br.myapplication.ui.film.FilmsViewModel
import com.br.myapplication.ui.planets.PlanetsViewModel
import com.br.myapplication.ui.species.SpeciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        FilmsViewModel(
            filmRepository = get(),
            filmsDao = get()
        )
    }

    viewModel {
        PlanetsViewModel(
            repository = get(),
            planetsDao = get()
        )
    }

    viewModel {
        SpeciesViewModel(
            repository = get(),
            speciesDao = get()
        )
    }

    viewModel {
        FavoritesViewModel(
            repository = get()
        )
    }
}