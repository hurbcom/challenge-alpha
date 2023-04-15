
package br.com.hurbandroidchallenge.domain.di

import br.com.hurbandroidchallenge.domain.use_case.characters.*
import br.com.hurbandroidchallenge.domain.use_case.films.*
import br.com.hurbandroidchallenge.domain.use_case.planets.*
import org.koin.dsl.module

val useCaseModule = module {

    // Characters
    single { GetCharactersUseCase(get()) }

    single { GetCharacterByUrlUseCase(get()) }

    single { SetCharacterLastSeenUseCase(get()) }

    single { SetFavoriteCharacterUseCase(get()) }

    single { GetFavoriteCharactersUseCase(get()) }

    single { GetLastSeenCharactersUseCase(get()) }

    // Films
    single { GetFilmsUseCase(get()) }

    single { GetFilmByUrlUseCase(get()) }

    single { SetFilmLastSeenUseCase(get()) }

    single { SetFavoriteFilmUseCase(get()) }

    single { GetFavoriteFilmsUseCase(get()) }

    single { GetLastSeenFilmsUseCase(get()) }

    // Planets
    single { GetPlanetsUseCase(get()) }

    single { GetPlanetByUrlUseCase(get()) }

    single { SetPlanetLastSeenUseCase(get()) }

    single { SetFavoritePlanetUseCase(get()) }

    single { GetFavoritesPlanetsUseCase(get()) }

    single { GetLastSeenPlanetsUseCase(get()) }

}