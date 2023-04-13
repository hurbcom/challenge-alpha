package br.com.hurbandroidchallenge.data.local.data_source

import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity

interface StarWarsBookLocalDataSource {

    suspend fun getHomeCategories(): List<HomeCategoriesEntity>

    suspend fun setHomeCategories(categories: List<HomeCategoriesEntity>, reset: Boolean)

    suspend fun getCharacters(): List<PeopleEntity>

    suspend fun setCharacters(characters: List<PeopleEntity>, reset: Boolean)

    suspend fun getFilms(): List<FilmEntity>

    suspend fun setFilms(films: List<FilmEntity>)

}