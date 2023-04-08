package com.example.hurbandroidchallenge.commom.model

import br.com.simpledex.commom.model.MessageError
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val message: String,
    val error: MessageError,
    val success: Boolean
)
