package com.example.hurb_challenge.app.data

import com.example.hurb_challenge.app.data.dto.ApiResponse
import com.example.hurb_challenge.app.data.dto.CharacterDto
import com.example.hurb_challenge.app.data.dto.FilmDto
import com.example.hurb_challenge.app.data.dto.StarshipDto
import com.example.hurb_challenge.app.data.dto.VehicleDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("people/")
    suspend fun getCharacters(@Query("page") page: Int): ApiResponse<CharacterDto>

    @GET("films/")
    suspend fun getFilms(@Query("page") page: Int): ApiResponse<FilmDto>

    @GET
    suspend fun getCharacter(@Url url: String): CharacterDto

    @GET
    suspend fun getVehicle(@Url url: String): VehicleDto

    @GET
    suspend fun getStarship(@Url url: String): StarshipDto
}
