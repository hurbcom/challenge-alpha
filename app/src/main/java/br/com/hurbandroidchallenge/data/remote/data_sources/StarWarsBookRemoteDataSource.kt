package br.com.hurbandroidchallenge.data.remote.data_sources

import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.data.remote.model.FilmResponse
import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesResponse
import br.com.hurbandroidchallenge.data.remote.model.PeopleResponse
import br.com.hurbandroidchallenge.data.remote.model.base.PagedListResponse

interface StarWarsBookRemoteDataSource {

    suspend fun getHomeCategories(url: String = ApiUrls.categories): HomeCategoriesResponse

    suspend fun getCharacters(url: String): PagedListResponse<PeopleResponse>

    suspend fun getFilms(url: String): PagedListResponse<FilmResponse>

}