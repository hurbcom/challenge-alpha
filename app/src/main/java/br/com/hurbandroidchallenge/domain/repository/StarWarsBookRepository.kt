package br.com.hurbandroidchallenge.domain.repository

import br.com.hurbandroidchallenge.domain.model.Categories
import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.model.base.PagedList
import kotlinx.coroutines.flow.Flow

interface StarWarsBookRepository {

    fun getHomeCategories(): Flow<List<Categories>>

    fun getCharacters(url: String): Flow<PagedList<People>>

    fun getCharacterById(url: String, fromRemote: Boolean): Flow<People>

    fun getFilms(url: String): Flow<PagedList<Film>>

    fun getFilmByUrl(url: String, fromRemote: Boolean): Flow<Film>

}