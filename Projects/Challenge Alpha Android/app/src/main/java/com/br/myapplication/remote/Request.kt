package com.br.myapplication.remote

import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

sealed class SafeResponse<out T> {
    data class Success<out T>(val value: T) : SafeResponse<T>()
    data class GenericError(val code: Int? = null, val error: Response<*>? = null) : SafeResponse<Nothing>()
    object NetworkError : SafeResponse<Nothing>()
}

suspend fun <T> safeRequest(request: suspend () -> T): SafeResponse<T> {
    return try {
        SafeResponse.Success(request())
    } catch (throwable: Exception) {
        Timber.e(throwable)

        return when (throwable) {
            is IOException -> {
                SafeResponse.NetworkError
            }
            is HttpException -> {
                SafeResponse.GenericError(throwable.code(), throwable.response())
            }
            else -> {
                SafeResponse.GenericError(null, null)
            }
        }
    }
}