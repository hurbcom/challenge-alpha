package br.com.hurbandroidchallenge.data.local.data_source

import br.com.hurbandroidchallenge.data.local.model.*
import br.com.hurbandroidchallenge.domain.model.Planet

interface StarWarsBookLocalDataSource {

    // Categories
    suspend fun getHomeCategories(): List<HomeCategoriesEntity>

    suspend fun insertHomeCategories(categories: List<HomeCategoriesEntity>)

    suspend fun clearCategories()

    // Characters
    suspend fun getCharacters(): List<PeopleEntity>

    suspend fun getCharacterById(id: Int): PeopleEntity

    suspend fun containsCharacter(id: Int): Boolean

    suspend fun insertCharacters(characters: List<PeopleEntity>)

    suspend fun clearCharacters()

    suspend fun getFavoriteCharacters(): List<PeopleEntity>

    suspend fun getLastSeenCharacters(): List<PeopleEntity>

    // Films
    suspend fun getFilms(): List<FilmEntity>

    suspend fun getFilmById(id: Int): FilmEntity

    suspend fun insertFilms(films: List<FilmEntity>)

    suspend fun containsFilm(id: Int): Boolean

    suspend fun clearFilms()

    suspend fun getFavoriteFilms(): List<FilmEntity>

    suspend fun getLastSeenFilms(): List<FilmEntity>

    // Planets
    suspend fun insertPlanets(planets: List<PlanetEntity>)

    suspend fun getPlanets(): List<PlanetEntity>

    suspend fun getPlanetById(id: Int): PlanetEntity

    suspend fun containsPlanet(id: Int): Boolean

    suspend fun clearPlanets()

    suspend fun getFavoritePlanets(): List<PlanetEntity>

    suspend fun getLastSeenPlanets(): List<PlanetEntity>

    // Generic
    suspend fun updateEntity(entity: UpdateEntity)

}