package com.br.myapplication.data.repository.favorites

import com.br.myapplication.data.dao.FilmsDao
import com.br.myapplication.data.dao.PlanetsDao
import com.br.myapplication.data.dao.SpeciesDao
import com.br.myapplication.data.mapper.mapToFavoriteList
import com.br.myapplication.data.model.Favorite

class FavoritesRepository(
    private val filmsDao: FilmsDao,
    private val speciesDao: SpeciesDao,
    private val planetsDao: PlanetsDao
): IFavoritesRepository {
    private suspend fun getFavoritesFilmsList(): List<Favorite> {
        return filmsDao.getFavoritesFilms().mapToFavoriteList()
    }

    private suspend fun getFavoritesSpeciesList(): List<Favorite> {
        return speciesDao.getFavoritesSpecies().mapToFavoriteList()
    }

    private suspend fun getFavoritesPlanetsList(): List<Favorite> {
        return planetsDao.getFavoritesPlanets().mapToFavoriteList()
    }

    override suspend fun getFavoritesList(): List<Favorite> {
        val films = getFavoritesFilmsList()
        val species = getFavoritesSpeciesList()
        val planets = getFavoritesPlanetsList()

        return films.plus(species)
            .plus(planets)
    }


}