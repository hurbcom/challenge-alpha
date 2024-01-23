package com.br.myapplication.data.repository.specie


import com.br.myapplication.data.model.Specie

interface ISpeciesRepository {
    suspend fun geSpecieList(page: String): List<Specie>
}