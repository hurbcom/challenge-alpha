package br.com.hurbandroidchallenge.data.local.data_source

import br.com.hurbandroidchallenge.data.local.dao.StarWarsBookDao
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity

class StarWarsBookLocalDataSourceImpl(
    private val dao: StarWarsBookDao,
) : StarWarsBookLocalDataSource {
    override suspend fun getHomeCategories(): List<HomeCategoriesEntity> {
        return dao.getCategories()
    }

    override suspend fun updateHomeCategories(categories: List<HomeCategoriesEntity>) {
        val currentCategories = getHomeCategories().map { it.url }
        val newCategories = categories.filter { !currentCategories.contains(it.url) }
        dao.insertNewCategories(newCategories)
    }

    override suspend fun getCharacters(): List<PeopleEntity> {
        return dao.getCharacters().sortedBy { it.id }
    }

    override suspend fun getCharacterById(id: Int): PeopleEntity {
        return dao.getCharacterById(id)
    }

    override suspend fun setCharacters(characters: List<PeopleEntity>) {
        val currentCharacters = getCharacters().map { it.id }
        val newCharacters = characters.filter { !currentCharacters.contains(it.id) }
        dao.insertNewCharacters(newCharacters)
    }

    override suspend fun getFilms(): List<FilmEntity> {
        return dao.getFilms().sortedBy { it.id }
    }

    override suspend fun getFilmById(id: Int): FilmEntity {
        return dao.getFilmById(id)
    }

    override suspend fun setFilms(films: List<FilmEntity>) {
        val currentFilms = getFilms().map { it.id }
        val newFilms = films.filter { !currentFilms.contains(it.id) }
        dao.insertNewFilms(newFilms)
    }


}