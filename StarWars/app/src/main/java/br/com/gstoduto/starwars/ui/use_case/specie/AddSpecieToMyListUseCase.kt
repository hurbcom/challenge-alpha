package br.com.gstoduto.starwars.ui.use_case.specie

interface AddSpecieToMyListUseCase {
    suspend fun addSpecieToMyList(id: String)
}