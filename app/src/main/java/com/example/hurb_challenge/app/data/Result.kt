package com.example.hurb_challenge.app.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class Result<out T>

sealed class Failure : Result<Nothing>()

data class HttpFailure(
    val errorCode: Int
) : Failure()

data object ConnectionFailure : Failure()

data object UnknownFailure : Failure()

data object Fetching : Result<Nothing>()

data class Success<out T>(val value: T) : Result<T>()

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> { Success(it) }
        .onStart { emit(Fetching) }
        .catch { failure ->
            when (failure) {
                is UnknownHostException -> emit(ConnectionFailure)
                is HttpException -> emit(HttpFailure(failure.code()))
                else -> emit(UnknownFailure)
            }
        }
}