package br.com.hurbandroidchallenge.data.local.data_source

import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity
import br.com.hurbandroidchallenge.data.local.model.PlanetEntity

interface StarWarsBookLocalDataSource {

    suspend fun getHomeCategories(): List<HomeCategoriesEntity>

    suspend fun updateHomeCategories(categories: List<HomeCategoriesEntity>)

    suspend fun clearCategories()

    suspend fun getCharacters(): List<PeopleEntity>

    suspend fun getCharacterById(id: Int): PeopleEntity

    suspend fun containsCharacter(id: Int): Boolean

    suspend fun updateCharacters(characters: List<PeopleEntity>)

    suspend fun clearCharacters()

    suspend fun getFilms(): List<FilmEntity>

    suspend fun getFilmById(id: Int): FilmEntity

    suspend fun updateFilms(films: List<FilmEntity>)

    suspend fun containsFilm(id: Int): Boolean

    suspend fun clearFilms()

    suspend fun updatePlanets(planets: List<PlanetEntity>)

    suspend fun getPlanets(): List<PlanetEntity>

    suspend fun getPlanetById(id: Int): PlanetEntity

    suspend fun containsPlanet(id: Int): Boolean

    suspend fun clearPlanets()

}