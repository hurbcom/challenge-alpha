package com.example.challenge_alpha.api

import com.example.challenge_alpha.model.Filters
import com.example.challenge_alpha.model.Header
import com.example.challenge_alpha.model.Pagination
import com.example.challenge_alpha.model.ResultDetail
import com.google.gson.annotations.SerializedName

// Data class para as respostas da API
class HurbResponse (
    @SerializedName("meta") val meta: Header = Header(),
    @SerializedName("filters") val filters: Filters = Filters(),
    @SerializedName("results") val resultDetail: List<ResultDetail> = emptyList(),
    @SerializedName("pagination") val pagination: Pagination = Pagination()
)