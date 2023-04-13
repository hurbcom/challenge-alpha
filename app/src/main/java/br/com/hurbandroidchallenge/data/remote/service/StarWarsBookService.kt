package br.com.hurbandroidchallenge.data.remote.service

import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.data.remote.model.FilmDto
import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesDto
import br.com.hurbandroidchallenge.data.remote.model.PeopleDto
import br.com.hurbandroidchallenge.data.remote.model.base.PagedListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface StarWarsBookService {

    @GET
    suspend fun getHomeCategories(@Url url: String = ApiUrls.categories): Response<HomeCategoriesDto>

    @GET
    suspend fun getCharacters(
        @Url url: String = ApiUrls.characters
    ): Response<PagedListResponse<PeopleDto>>

    @GET
    suspend fun getCharacterByUrl(@Url url: String): Response<PeopleDto>

    @GET
    suspend fun getFilms(
        @Url url: String = ApiUrls.films
    ): Response<PagedListResponse<FilmDto>>

    @GET
    suspend fun getFilmByUrl(@Url url: String): Response<FilmDto>

}