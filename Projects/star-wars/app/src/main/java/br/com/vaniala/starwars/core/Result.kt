package br.com.vaniala.starwars.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val msg: Throwable?) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

fun <T> Flow<Result<T>>.doOnSuccess(action: suspend (T) -> Unit): Flow<Result<T>> =
    transform { result ->
        if (result is Result.Success) {
            action(result.data)
        }
        return@transform emit(result)
    }

fun <T> Flow<Result<T>>.doOnFailure(action: suspend (Throwable?) -> Unit): Flow<Result<T>> =
    transform { result ->
        if (result is Result.Failure) {
            action(result.msg)
        }
        return@transform emit(result)
    }

fun <T> Flow<Result<T>>.doOnLoading(action: suspend () -> Unit): Flow<Result<T>> =
    transform { result ->
        if (result is Result.Loading) {
            action()
        }
        return@transform emit(result)
    }