package com.vdemelo.starwarswiki.domain.entity.model

import com.vdemelo.starwarswiki.data.remote.response.SpeciesListResponse
import com.vdemelo.starwarswiki.utils.nonNullOrEmpty

class SpeciesList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Species>
) {

    constructor(response: SpeciesListResponse): this(
        count = response.count ?: 0,
        next = response.next,
        previous = response.previous,
        results = response.results.nonNullOrEmpty().toModel()
    )

}
