package br.com.hurbandroidchallenge.data.local.data_source

import br.com.hurbandroidchallenge.data.local.dao.StarWarsBookDao
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity
import br.com.hurbandroidchallenge.data.local.model.PlanetEntity
import br.com.hurbandroidchallenge.data.local.preferences.PreferencesWrapper

class StarWarsBookLocalDataSourceImpl(
    private val dao: StarWarsBookDao,
) : StarWarsBookLocalDataSource {

    private val preferences = PreferencesWrapper.getInstance()

    override suspend fun getHomeCategories() = dao.getCategories()

    override suspend fun updateHomeCategories(categories: List<HomeCategoriesEntity>) {
        val currentCategories = getHomeCategories().map { it.url }
        val newCategories = categories.filter { !currentCategories.contains(it.url) }
        if (newCategories.isEmpty())
            preferences.categoriesIsUpToDate()
        dao.insertNewCategories(newCategories)
    }

    override suspend fun getCharacters() = dao.getCharacters().sortedBy { it.id }

    override suspend fun getCharacterById(id: Int) = dao.getCharacterById(id)

    override suspend fun updateCharacters(characters: List<PeopleEntity>) {
        val currentCharacters = getCharacters().map { it.id }
        val newCharacters = characters.filter { !currentCharacters.contains(it.id) }
        dao.insertNewCharacters(newCharacters)
    }

    override suspend fun getFilms() = dao.getFilms().sortedBy { it.id }

    override suspend fun getFilmById(id: Int) = dao.getFilmById(id)

    override suspend fun updateFilms(films: List<FilmEntity>) {
        val currentFilms = getFilms().map { it.id }
        val newFilms = films.filter { !currentFilms.contains(it.id) }
        dao.insertNewFilms(newFilms)
    }

    override suspend fun containsCharacter(id: Int): Boolean {
        return dao.containsCharacter(id) == 1
    }

    override suspend fun containsFilm(id: Int): Boolean {
        return dao.containsFilm(id) == 1
    }

    override suspend fun updatePlanets(planets: List<PlanetEntity>) {
        val currentFilms = getPlanets().map { it.id }
        val newPlanets = planets.filter { !currentFilms.contains(it.id) }
        dao.insertNewPlanets(newPlanets)
    }

    override suspend fun getPlanets() = dao.getPlanets().sortedBy { it.id }


    override suspend fun getPlanetById(id: Int) = dao.getPlanetById(id)

    override suspend fun containsPlanet(id: Int): Boolean {
        return dao.containsPlanets(id) == 1
    }

}