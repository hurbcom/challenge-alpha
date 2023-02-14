package com.example.core.base

sealed class BaseResponse<out T> {
    class Loading<T> : BaseResponse<T>()
    data class Success<T>(val data: T) : BaseResponse<T>()
    data class Error<T>(val error: Throwable) : BaseResponse<T>()
}
