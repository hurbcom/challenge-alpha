package br.com.hurbandroidchallenge.data.local.data_source

import br.com.hurbandroidchallenge.data.local.dao.StarWarsBookDao
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity

class StarWarsBookLocalDataSourceImpl(
    private val dao: StarWarsBookDao
) : StarWarsBookLocalDataSource {
    override suspend fun getHomeCategories(): List<HomeCategoriesEntity> {
        return dao.getCategories()
    }

    override suspend fun setHomeCategories(categories: List<HomeCategoriesEntity>, reset: Boolean) {
        if (reset)
            dao.deleteAllCategories()
        dao.upsertAllCategories(categories)
    }

    override suspend fun getCharacters(): List<PeopleEntity> {
        return dao.getCharacters()
    }

    override suspend fun setCharacters(characters: List<PeopleEntity>, reset: Boolean) {
        if (reset)
            dao.deleteAllPeople()
        dao.upsertAllPeople(characters)
    }

    override suspend fun getFilms(): List<FilmEntity> {
        return dao.getFilms()
    }

    override suspend fun setFilms(films: List<FilmEntity>, reset: Boolean) {
        if (reset)
            dao.deleteAllFilms()
        dao.upsertAllFilms(films)
    }


}