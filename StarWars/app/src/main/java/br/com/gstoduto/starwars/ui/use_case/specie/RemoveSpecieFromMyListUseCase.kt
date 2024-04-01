package br.com.gstoduto.starwars.ui.use_case.specie

interface RemoveSpecieFromMyListUseCase {
    suspend fun removeSpecieFromMyList(id: String)
}