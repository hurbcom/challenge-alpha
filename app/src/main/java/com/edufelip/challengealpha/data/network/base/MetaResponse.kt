package com.edufelip.challengealpha.data.network.base

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MetaResponse(
    @SerializedName("limit") val limit: Long,
    @SerializedName("next") val next: String?,
    @SerializedName("offset") val offset: Long,
    @SerializedName("totalCount") val totalCount: Long
) : Serializable