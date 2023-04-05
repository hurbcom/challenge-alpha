package com.example.starwars.retrofit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class ApiResult<out T>(val data: T?, val throwable: Throwable?) {

    data class Success<out T>(private val _data: T?) : ApiResult<T>(
        data = _data,
        throwable = null
    )

    data class Error(private val exception: Throwable?) : ApiResult<Nothing>(
        data = null,
        throwable = exception
    )

    object Loading : ApiResult<Nothing>(
        data = null,
        throwable = null
    )
}

fun <T, R> Flow<ApiResult<T>>.apiResultTransform(transform: (T?) -> R?): Flow<ApiResult<R>> {
    return this.map { result ->
        when (result) {
            is ApiResult.Loading -> ApiResult.Loading
            is ApiResult.Error -> ApiResult.Error(result.throwable)
            is ApiResult.Success -> ApiResult.Success<R>(transform(result.data))
        }
    }
}