package com.br.myapplication.data.remote

import com.br.myapplication.data.model.Film
import com.br.myapplication.data.model.FilmsPage
import com.br.myapplication.data.model.Planet
import com.br.myapplication.data.model.Specie
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("films")
    suspend fun getFilms(@Query("page") page: String): FilmsPage

    @GET("planets")
    suspend fun getPlanets(@Query("page") page: String): List<Planet>

    @GET("species")
    suspend fun getSpecies(@Query("page") page: String): List<Specie>

}