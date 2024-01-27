package com.vdemelo.starwarswiki.data.remote.response

import com.google.gson.annotations.SerializedName

class PlanetsListResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<PlanetResponse?>?
)
