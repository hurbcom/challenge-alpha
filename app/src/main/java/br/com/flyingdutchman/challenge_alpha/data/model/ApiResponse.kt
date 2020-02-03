package br.com.flyingdutchman.challenge_alpha.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("filters")
    val filters: Filters,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("pagination")
    val pagination: Pagination,
    @SerializedName("results")
    val results: List<Result>
)