package br.com.hurbandroidchallenge.data.local.data_source

import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity

interface StarWarsBookLocalDataSource {

    suspend fun getHomeCategories(): List<HomeCategoriesEntity>

    suspend fun updateHomeCategories(categories: List<HomeCategoriesEntity>)

    suspend fun getCharacters(): List<PeopleEntity>

    suspend fun getCharacterById(id: Int): PeopleEntity

    suspend fun setCharacters(characters: List<PeopleEntity>)

    suspend fun getFilms(): List<FilmEntity>

    suspend fun getFilmById(id: Int): FilmEntity

    suspend fun setFilms(films: List<FilmEntity>)

}