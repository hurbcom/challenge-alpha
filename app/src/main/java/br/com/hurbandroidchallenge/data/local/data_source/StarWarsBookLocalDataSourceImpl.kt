package br.com.hurbandroidchallenge.data.local.data_source

import br.com.hurbandroidchallenge.commom.contants.Constants
import br.com.hurbandroidchallenge.commom.util.date.DateUtils
import br.com.hurbandroidchallenge.data.local.dao.StarWarsBookDao
import br.com.hurbandroidchallenge.data.local.model.*
import br.com.hurbandroidchallenge.data.local.preferences.PreferencesWrapper

class StarWarsBookLocalDataSourceImpl(
    private val dao: StarWarsBookDao,
) : StarWarsBookLocalDataSource {

    private val preferences = PreferencesWrapper.getInstance()

    // Categories
    override suspend fun getHomeCategories() = dao.getCategories()

    override suspend fun insertHomeCategories(categories: List<HomeCategoriesEntity>) {
        val currentCategories = getHomeCategories().map { it.url }
        val newCategories = categories.filter { !currentCategories.contains(it.url) }
        if (newCategories.isEmpty())
            preferences.categoriesIsUpToDate()
        dao.insertNewCategories(newCategories)
    }

    override suspend fun clearCategories() {
        preferences.clearCategories()
        dao.clearCategories()
    }

    // Characters
    override suspend fun getCharacters() = dao.getCharacters().sortedBy { it.id }

    override suspend fun getCharacterById(id: Int) = dao.getCharacterById(id)

    override suspend fun insertCharacters(characters: List<PeopleEntity>) {
        val currentCharacters = getCharacters().map { it.id }
        val newCharacters = characters.filter { !currentCharacters.contains(it.id) }
        dao.insertNewCharacters(newCharacters)
    }

    override suspend fun clearCharacters() {
        preferences.clearCharacters()
        dao.clearCharacters()
    }

    override suspend fun getFavoriteCharacters(): List<PeopleEntity> {
        return dao.getCharacters().filter { it.favorite }
    }

    override suspend fun getLastSeenCharacters(): List<PeopleEntity> {
        return dao.getCharacters().filter {
            val days = DateUtils.daysUntilToday(it.lastSeen)
            days <= Constants.LAST_SEEN_DAYS_INTERVAL
        }
    }

    // Films
    override suspend fun getFilms() = dao.getFilms().sortedBy { it.id }

    override suspend fun getFilmById(id: Int) = dao.getFilmById(id)

    override suspend fun insertFilms(films: List<FilmEntity>) {
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

    override suspend fun clearFilms() {
        preferences.clearFilms()
        dao.clearFilms()
    }

    override suspend fun getFavoriteFilms(): List<FilmEntity> {
        return dao.getFilms().filter { it.favorite }
    }

    override suspend fun getLastSeenFilms(): List<FilmEntity> {
        return dao.getFilms().filter {
            val days = DateUtils.daysUntilToday(it.lastSeen)
            days <= Constants.LAST_SEEN_DAYS_INTERVAL
        }
    }

    // Planets
    override suspend fun insertPlanets(planets: List<PlanetEntity>) {
        val currentFilms = getPlanets().map { it.id }
        val newPlanets = planets.filter { !currentFilms.contains(it.id) }
        dao.insertNewPlanets(newPlanets)
    }

    override suspend fun getPlanets() = dao.getPlanets().sortedBy { it.id }


    override suspend fun getPlanetById(id: Int) = dao.getPlanetById(id)

    override suspend fun containsPlanet(id: Int): Boolean {
        return dao.containsPlanets(id) == 1
    }

    override suspend fun clearPlanets() {
        preferences.clearPlanets()
        dao.clearPlanets()
    }

    override suspend fun getFavoritePlanets(): List<PlanetEntity> {
        return dao.getPlanets().filter { it.favorite }
    }

    override suspend fun getLastSeenPlanets(): List<PlanetEntity> {
        return dao.getPlanets().filter {
            val days = DateUtils.daysUntilToday(it.lastSeen)
            days <= Constants.LAST_SEEN_DAYS_INTERVAL
        }
    }

    override suspend fun updateEntity(entity: UpdateEntity) {
        dao.updateCharacter(entity)
    }

}