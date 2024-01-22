package br.com.mdr.starwars.data.remote.model

import br.com.mdr.starwars.domain.model.Character

data class CharacterResponse(
    override val count: Int,
    override val next: String?,
    override val previous: String?,
    val results: List<Character>
): IBaseApiResponse
