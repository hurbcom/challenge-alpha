package br.com.gstoduto.starwars.network.model.specie

data class SpeciesResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<SpecieResponse>
)