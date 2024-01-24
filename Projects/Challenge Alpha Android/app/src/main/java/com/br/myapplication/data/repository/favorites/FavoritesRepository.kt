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
    private fun getFavoritesFilmsList(): List<Favorite> {
        return filmsDao.getFavoritesFilms().mapToFavoriteList()
    }

    private fun getFavoritesSpeciesList(): List<Favorite> {
        return speciesDao.getFavoritesSpecies().mapToFavoriteList()
    }

    private fun getFavoritesPlanetsList(): List<Favorite> {
        return planetsDao.getFavoritesPlanets().mapToFavoriteList()
    }

    override suspend fun getFavoritesList(): List<Favorite> {
        return getFavoritesFilmsList()
            .plus(getFavoritesPlanetsList())
            .plus(getFavoritesSpeciesList())
    }


}