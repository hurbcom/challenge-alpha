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

    override suspend fun setHomeCategories(categories: List<HomeCategoriesEntity>, reset: Boolean) {
        dao.deleteAllCategories()
        dao.upsertAllCategories(categories)
    }

    override suspend fun getCharacters(): List<PeopleEntity> {
        return dao.getCharacters()
    }

    override suspend fun getCharacterById(id: Int): PeopleEntity {
        return dao.getCharacterById(id)
    }

    override suspend fun setCharacters(characters: List<PeopleEntity>, reset: Boolean) {
        if (reset)
            dao.deleteAllCharacters()
        val currentCharacters = getCharacters()
        dao.deleteAllCharacters()
        dao.upsertAllCharacters(currentCharacters.plus(characters).distinct())
    }

    override suspend fun getFilms(): List<FilmEntity> {
        return dao.getFilms()
    }

    override suspend fun getFilmById(id: Int): FilmEntity {
        return dao.getFilmById(id)
    }

    override suspend fun setFilms(films: List<FilmEntity>) {
        dao.deleteAllFilms()
        dao.upsertAllFilms(films)
    }


}