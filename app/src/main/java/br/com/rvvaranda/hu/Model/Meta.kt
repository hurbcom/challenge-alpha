package br.com.rvvaranda.hu.Model

import com.google.gson.annotations.SerializedName


data class Meta(
    val pagination: Pagination,
    @SerializedName("results")
    val hotels: ArrayList<Hotel>
)
