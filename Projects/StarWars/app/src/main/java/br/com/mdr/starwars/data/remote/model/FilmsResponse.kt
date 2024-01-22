package br.com.mdr.starwars.data.remote.model

import br.com.mdr.starwars.domain.model.Film

data class FilmsResponse(
    override val count: Int,
    override val next: String?,
    override val previous: String?,
    val results: List<Film>
) : IBaseApiResponse
