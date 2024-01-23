package com.br.myapplication.data.repository.planet

import com.br.myapplication.data.model.Planet

interface IPlanetRepository {
    suspend fun getPlanetList(page: String): List<Planet>
}