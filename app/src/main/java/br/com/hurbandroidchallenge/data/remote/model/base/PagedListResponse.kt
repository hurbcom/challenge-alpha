package br.com.hurbandroidchallenge.data.remote.model.base

import com.google.gson.annotations.SerializedName

class PagedListResponse<T>(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: List<T>
)