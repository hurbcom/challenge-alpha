package br.com.simpledex.commom.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MessageError(
    @SerializedName("error_code") val code: Int? = null,
    @SerializedName("error_message") val message: String? = null
)