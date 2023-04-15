package br.com.hurbandroidchallenge.data.local.data_source

import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity
import br.com.hurbandroidchallenge.data.local.model.PlanetEntity
import br.com.hurbandroidchallenge.data.local.model.UpdateEntity

interface StarWarsBookLocalDataSource {

    // Characters
    suspend fun getCharacters(): List<PeopleEntity>

    suspend fun getCharacterById(id: Int): PeopleEntity

    suspend fun containsCharacter(id: Int): Boolean

    suspend fun insertCharacters(characters: List<PeopleEntity>)

    suspend fun clearCharacters()

    suspend fun getFavoriteCharacters(): List<PeopleEntity>

    suspend fun getLastSeenCharacters(): List<PeopleEntity>

    suspend fun updateCharacter(entity: UpdateEntity)

    // Films
    suspend fun getFilms(): List<FilmEntity>

    suspend fun getFilmById(id: Int): FilmEntity

    suspend fun insertFilms(films: List<FilmEntity>)

    suspend fun containsFilm(id: Int): Boolean

    suspend fun clearFilms()

    suspend fun getFavoriteFilms(): List<FilmEntity>

    suspend fun getLastSeenFilms(): List<FilmEntity>

    suspend fun updateFilm(entity: UpdateEntity)

    // Planets
    suspend fun insertPlanets(planets: List<PlanetEntity>)

    suspend fun getPlanets(): List<PlanetEntity>

    suspend fun getPlanetById(id: Int): PlanetEntity

    suspend fun containsPlanet(id: Int): Boolean

    suspend fun clearPlanets()

    suspend fun getFavoritePlanets(): List<PlanetEntity>

    suspend fun getLastSeenPlanets(): List<PlanetEntity>

    suspend fun updatePlanet(entity: UpdateEntity)

}