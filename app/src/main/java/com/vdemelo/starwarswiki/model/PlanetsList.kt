package com.vdemelo.starwarswiki.model

import com.vdemelo.starwarswiki.data.remote.response.PlanetsListResponse
import com.vdemelo.starwarswiki.utils.nonNullOrEmpty

class PlanetsList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Planet>
) {

    constructor(response: PlanetsListResponse): this(
        count = response.count ?: 0,
        next = response.next,
        previous = response.previous,
        results = response.results.nonNullOrEmpty().toModel()
    )

}
