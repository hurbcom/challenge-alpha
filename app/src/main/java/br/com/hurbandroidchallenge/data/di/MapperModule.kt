package br.com.hurbandroidchallenge.data.di

import br.com.hurbandroidchallenge.data.mapper.characters.PeopleDtoToEntityMapper
import br.com.hurbandroidchallenge.data.mapper.characters.PeopleEntityToPeopleMapper
import br.com.hurbandroidchallenge.data.mapper.films.FilmDtoToEntityMapper
import br.com.hurbandroidchallenge.data.mapper.films.FilmEntityToFilmMapper
import br.com.hurbandroidchallenge.data.mapper.planets.PlanetDtoToEntityMapper
import br.com.hurbandroidchallenge.data.mapper.planets.PlanetEntityToPlanetMapper
import org.koin.dsl.module

val mapperModule = module {

    single { PeopleDtoToEntityMapper() }

    single { PeopleEntityToPeopleMapper() }

    single { FilmDtoToEntityMapper() }

    single { FilmEntityToFilmMapper() }

    single { PlanetEntityToPlanetMapper() }

    single { PlanetDtoToEntityMapper() }

}