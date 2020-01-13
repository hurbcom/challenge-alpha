package com.example.challenge_alpha.model

import com.google.gson.annotations.SerializedName

/**
 * A classe [Pagination] define os dados de paginação recebidos do servidor.
 *
 * A classe [HurbResponse] agrupa todos os dados recebidos no JSON do servidor através do retrofit
 * [HurbCall]
 *
 */

data class Pagination(
    @SerializedName("count") val count: Int? = null,
    @SerializedName("firstPage") val firstPage: String? = null,
    @SerializedName("nextPage") val nextPage: String? = null,
    @SerializedName("previousPage") val previousPage: String? = null,
    @SerializedName("lastPage") val lastPage: String? = null
    )