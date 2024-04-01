package br.com.gstoduto.starwars.network.services

import br.com.gstoduto.starwars.network.model.specie.SpeciesResponse
import retrofit2.http.GET

interface SpecieService {
    @GET("species")
    suspend fun findAll(): SpeciesResponse
}