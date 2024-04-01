package br.com.gstoduto.starwars.network.services

import br.com.gstoduto.starwars.network.model.movie.MoviesResponse
import retrofit2.http.GET

interface MovieService {

    @GET("films")
    suspend fun findAll(): MoviesResponse
}