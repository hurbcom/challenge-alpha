package com.github.felipehjcosta.huchallenge.base.hotels

import com.google.gson.annotations.SerializedName

data class SearchApiResponseWrapper(
    @SerializedName("results") val results: List<SearchApiResponseResult> = emptyList()
)