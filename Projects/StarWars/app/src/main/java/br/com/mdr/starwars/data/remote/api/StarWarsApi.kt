package br.com.mdr.starwars.data.remote.api

import br.com.mdr.starwars.data.remote.model.BaseApiResponse
import br.com.mdr.starwars.data.remote.model.CategoryResponse
import br.com.mdr.starwars.domain.model.Film
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {
    @GET("api")
    suspend fun getCategories(): CategoryResponse

    @GET("api/films/")
    suspend fun getFilms(@Query("page") page: Int): BaseApiResponse<Film>
}
