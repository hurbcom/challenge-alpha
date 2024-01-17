package com.wesleyerick.podracer.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> safeRunDispatcher(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend CoroutineScope.() -> (T),
) = withContext(dispatcher) {
    return@withContext try {
        val result = block()
        Result.Success(result)
    } catch (ex: Exception) {
        Result.Failure(ex)
    }
}

sealed class Result<out T> {
    class Success<T>(val data: T) : Result<T>()
    class Failure(val error: Exception, val errorMessage: String = "") : Result<Nothing>()
}
