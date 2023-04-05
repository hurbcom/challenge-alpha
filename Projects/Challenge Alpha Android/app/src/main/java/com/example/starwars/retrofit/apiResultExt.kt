package com.example.starwars.retrofit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

suspend fun <T> Flow<ApiResult<T>>.apiCollect(
    onLoading: () -> Unit = {},
    onError: (Throwable) -> Unit = {},
    onSuccessful: (T?) -> Unit = {}
) {
    this.catch {
        onError(it)
    }.collect {
        when (it) {
            is ApiResult.Success -> onSuccessful(it.data)
            is ApiResult.Error -> onError(it.throwable ?: Exception())
            is ApiResult.Loading -> onLoading()
        }
    }
}

fun <T> loadingEmitted(): Flow<ApiResult<T>> {
    return flow { emit(ApiResult.Loading) }
}

fun <T, R> errorEmitted(apiResult: ApiResult<T>): Flow<ApiResult<R>> {
    return flow { emit(ApiResult.Error(apiResult.throwable)) }
}