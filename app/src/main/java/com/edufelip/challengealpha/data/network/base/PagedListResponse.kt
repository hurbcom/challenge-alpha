package com.edufelip.challengealpha.data.network.base

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PagedListResponse<T>(
    @SerializedName("next") val next: String?,
    @SerializedName("results") val objects: List<T>
) : Serializable