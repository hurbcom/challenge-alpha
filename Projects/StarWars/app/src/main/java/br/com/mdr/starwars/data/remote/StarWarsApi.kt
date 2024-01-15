package br.com.mdr.starwars.data.remote

import br.com.mdr.starwars.data.remote.model.CategoryResponse
import retrofit2.http.GET

interface StarWarsApi {
    @GET("api")
    suspend fun getCategories(): CategoryResponse
}
