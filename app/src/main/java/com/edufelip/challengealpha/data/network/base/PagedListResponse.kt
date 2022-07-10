package com.edufelip.challengealpha.data.network.base

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PagedListResponse<T>(
    @SerializedName("meta") val meta: MetaResponse,
    @SerializedName("objects") val objects: List<T>
) : Serializable