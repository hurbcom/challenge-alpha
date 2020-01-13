package com.example.challenge_alpha.model

import com.google.gson.annotations.SerializedName

/**
 * A classe [Filters] define os dados de filtro recebidos do servidor.
 *
 * A classe [HurbResponse] agrupa todos os dados recebidos no JSON do servidor através do retrofit
 * [HurbCall]
 *
 */
data class Filters(
    @SerializedName("amenities") val amenities: List<FilterDescription> = emptyList(),
    @SerializedName("attributes") val attributes: List<FilterDescription> = emptyList(),
    @SerializedName("countries") val countries: List<FilterDescription> = emptyList(),
    @SerializedName("cities") val cities: List<FilterDescription> = emptyList(),
    @SerializedName("departureCities") val departureCities: List<FilterDescription> = emptyList(),
    @SerializedName("duration") val duration: List<FilterDescription> = emptyList(),
    @SerializedName("food") val food: List<FilterDescription> = emptyList(),
    @SerializedName("people") val people: List<FilterDescription> = emptyList(),
    @SerializedName("prices") val prices: List<Prices> = emptyList(),
    @SerializedName("productType") val productType: List<FilterDescription> = emptyList(),
    @SerializedName("regulation") val regulation: List<FilterDescription> = emptyList(),
    @SerializedName("rooms") val rooms: List<FilterDescription> = emptyList(),
    @SerializedName("stars") val stars: List<FilterDescription> = emptyList(),
    @SerializedName("states") val states: List<FilterDescription> = emptyList()
    )

data class FilterDescription(
    @SerializedName("term") val description: String? = null,
    @SerializedName("filter") val filterString: String? = null,
    @SerializedName("count") val count: Int? = null
)

data class Prices(
    @SerializedName("min") val min: Int? = null,
    @SerializedName("maxExclusive") val maxExclusive: Int? = null,
    @SerializedName("filter") val filterString: String? = null,
    @SerializedName("count") val count: Int? = null
)