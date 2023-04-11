package com.example.challengealphaandroid.model

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

enum class Status {
    SUCCESS,
    ERROR
}

class ResultWithStatus<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
) {
    companion object {

        fun <T> success(data: T): ResultWithStatus<T> =
            ResultWithStatus(Status.SUCCESS, data)

        fun <T> error(message: String?): ResultWithStatus<T> =
            ResultWithStatus(Status.ERROR, message = message)
    }
}