package com.br.myapplication.data.repository.planet

import com.br.myapplication.data.mapper.mapListWithImage
import com.br.myapplication.data.model.Film
import com.br.myapplication.data.model.Planet
import com.br.myapplication.data.remote.ApiServices

class PlanetRepository(
    private val apiService: ApiServices
): IPlanetRepository {

    override suspend fun getPlanetList(page: String): List<Planet> {
        return apiService.getPlanets(page).movieList.mapListWithImage()
    }
}