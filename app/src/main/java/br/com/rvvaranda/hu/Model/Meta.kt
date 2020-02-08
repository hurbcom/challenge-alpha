package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("filters")
    val filters: Filters,
    @SerializedName("meta")
    val meta: MetaX,
    @SerializedName("pagination")
    val pagination: Pagination,
    @SerializedName("results")
    val hotels: ArrayList<Hotel>
)