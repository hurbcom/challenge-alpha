package com.example.core.base.data

sealed class BaseResult<out T> {
    data class Success<out T>(val data: T, val extraData: HashMap<String, String?>) :
        BaseResult<T>()

    data class Error(val error: Throwable) : BaseResult<Nothing>()
    object Loading : BaseResult<Nothing>()
}
