package br.com.hurbandroidchallenge.domain.repository

import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.HomeCategories
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.model.base.PagedList
import kotlinx.coroutines.flow.Flow

interface StarWarsBookRepository {

    fun getHomeCategories(): Flow<HomeCategories>

    fun getCharacters(url: String): Flow<PagedList<People>>

    fun getFilms(url: String): Flow<PagedList<Film>>

}