package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @SerializedName("meta") val meta: Meta,
    @SerializedName("filters") val filters: Filter,
    @SerializedName("results") val results: List<Result>,
    @SerializedName("pagination") val pagination: Pagination
)

data class SuggestionResponse(
    @SerializedName("suggestions") val suggestions: List<Suggestion>,
    @SerializedName("total") val total: Int,
    @SerializedName("search") val search: String,
    @SerializedName("packages") val packages: List<String>
)