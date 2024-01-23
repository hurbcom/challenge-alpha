package com.br.myapplication.data.repository.specie

import com.br.myapplication.data.mapper.mapListWithImage
import com.br.myapplication.data.model.Film
import com.br.myapplication.data.model.Specie
import com.br.myapplication.data.remote.ApiServices

class SpeciesRepository(
    private val apiService: ApiServices
): ISpeciesRepository {

    override suspend fun geSpecieList(page: String): List<Specie> {
        return apiService.getSpecies(page).movieList.mapListWithImage()
    }
}