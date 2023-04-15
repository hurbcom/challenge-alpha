package br.com.hurbandroidchallenge.domain.di

import br.com.hurbandroidchallenge.presentation.screens.character.detail.CharacterDetailViewModel
import br.com.hurbandroidchallenge.presentation.screens.character.list.CharactersViewModel
import br.com.hurbandroidchallenge.presentation.screens.film.detail.FilmDetailViewModel
import br.com.hurbandroidchallenge.presentation.screens.film.list.FilmsViewModel
import br.com.hurbandroidchallenge.presentation.screens.home.favorites.FavoritesViewModel
import br.com.hurbandroidchallenge.presentation.screens.home.last_seen.LastSeenViewModel
import br.com.hurbandroidchallenge.presentation.screens.planet.detail.PlanetDetailViewModel
import br.com.hurbandroidchallenge.presentation.screens.planet.list.PlanetsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        CharactersViewModel(
            getCharactersUseCase = get()
        )
    }

    viewModel {
        FilmsViewModel(
            getFilmsUseCase = get()
        )
    }

    viewModel { (url: String) ->
        CharacterDetailViewModel(
            getCharacterByUrlUseCase = get(),
            getFilmByUrlUseCase = get(),
            getPlanetByUrlUseCase = get(),
            setCharacterLastSeenUseCase = get(),
            setFavoriteCharacterUseCase = get(),
            url = url
        )
    }

    viewModel { (url: String) ->
        FilmDetailViewModel(
            getCharacterByUrlUseCase = get(),
            getFilmByUrlUseCase = get(),
            getPlanetByUrlUseCase = get(),
            setFilmLastSeenUseCase = get(),
            setFavoriteFilmsUseCase = get(),
            url = url
        )
    }

    viewModel {
        PlanetsViewModel(
            getPlanetsUseCase = get()
        )
    }

    viewModel { (url: String) ->
        PlanetDetailViewModel(
            getPlanetByUrlUseCase = get(),
            getCharacterByUrlUseCase = get(),
            getFilmByUrlUseCase = get(),
            setFavoritePlanetUseCase = get(),
            setLastSeenUseCase = get(),
            url = url
        )
    }

    viewModel {
        FavoritesViewModel(
            getFavoriteCharactersUseCase = get(),
            getFavoriteFilmsUseCase = get(),
            getFavoritesPlanetsUseCase = get()
        )
    }

    viewModel {
        LastSeenViewModel(
            getLastSeenCharactersUseCase = get(),
            getLastSeenFilmsUseCase = get(),
            getLastSeenPlanetsUseCase = get()
        )
    }

}