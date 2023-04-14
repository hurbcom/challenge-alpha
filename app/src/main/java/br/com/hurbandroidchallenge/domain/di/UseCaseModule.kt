
package br.com.hurbandroidchallenge.domain.di

import br.com.hurbandroidchallenge.domain.use_case.categories.GetHomeCategoriesUseCase
import br.com.hurbandroidchallenge.domain.use_case.characters.*
import br.com.hurbandroidchallenge.domain.use_case.films.*
import br.com.hurbandroidchallenge.domain.use_case.planets.*
import org.koin.dsl.module

val useCaseModule = module {

    single { GetHomeCategoriesUseCase(get()) }

    // Characters
    single { GetCharactersUseCase(get()) }

    single { GetCharacterByUrlUseCase(get()) }

    single { CharacterLastSeenUseCase(get()) }

    single { FavoriteCharacterUseCase(get()) }

    single { GetFavoriteCharactersUseCase(get()) }

    single { GetLastSeenCharactersUseCase(get()) }

    // Films
    single { GetFilmsUseCase(get()) }

    single { GetFilmByUrlUseCase(get()) }

    single { FilmLastSeenUseCase(get()) }

    single { FavoriteFilmUseCase(get()) }

    single { GetFavoriteFilmsUseCase(get()) }

    single { GetLastSeenFilmsUseCase(get()) }

    // Planets
    single { GetPlanetsUseCase(get()) }

    single { GetPlanetByUrlUseCase(get()) }

    single { PlanetLastSeenUseCase(get()) }

    single { FavoritePlanetUseCase(get()) }

    single { GetFavoritesPlanetsUseCase(get()) }

    single { GetLastSeenPlanetsUseCase(get()) }

}