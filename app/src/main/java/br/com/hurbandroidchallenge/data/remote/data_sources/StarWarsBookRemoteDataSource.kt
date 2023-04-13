package br.com.hurbandroidchallenge.data.remote.data_sources

import br.com.hurbandroidchallenge.data.remote.model.FilmDto
import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesDto
import br.com.hurbandroidchallenge.data.remote.model.PeopleDto
import br.com.hurbandroidchallenge.data.remote.model.base.PagedListResponse

interface StarWarsBookRemoteDataSource {

    suspend fun getHomeCategories(): HomeCategoriesDto

    suspend fun getCharacters(url: String): PagedListResponse<PeopleDto>

    suspend fun getFilms(url: String): PagedListResponse<FilmDto>

}