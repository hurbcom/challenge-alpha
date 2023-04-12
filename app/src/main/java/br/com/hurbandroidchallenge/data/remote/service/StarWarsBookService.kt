package br.com.hurbandroidchallenge.data.remote.service

import br.com.hurbandroidchallenge.data.remote.model.FilmResponse
import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesResponse
import br.com.hurbandroidchallenge.data.remote.model.PeopleResponse
import br.com.hurbandroidchallenge.data.remote.model.base.PagedListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface StarWarsBookService {

    @GET
    suspend fun getHomeCategories(@Url url: String): Response<HomeCategoriesResponse>

    @GET
    suspend fun getCharacters(@Url url: String): Response<PagedListResponse<PeopleResponse>>

    @GET
    suspend fun getCharacterDetail(@Url url: String): Response<PeopleResponse>

    @GET
    suspend fun getFilms(@Url url: String): Response<PagedListResponse<FilmResponse>>

    @GET
    suspend fun getFilmDetail(@Url url: String): Response<FilmResponse>

}