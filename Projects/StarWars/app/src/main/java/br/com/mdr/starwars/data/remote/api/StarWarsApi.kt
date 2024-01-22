package br.com.mdr.starwars.data.remote.api

import br.com.mdr.starwars.data.remote.model.CategoryResponse
import br.com.mdr.starwars.data.remote.model.CharacterResponse
import br.com.mdr.starwars.data.remote.model.FilmsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {
    @GET("api")
    suspend fun getCategories(): CategoryResponse

    @GET("api/films/")
    suspend fun getFilms(@Query("page") page: Int): FilmsResponse//BaseApiResponse<Film>

    @GET("api/people/")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponse//BaseApiResponse<Character>
}
