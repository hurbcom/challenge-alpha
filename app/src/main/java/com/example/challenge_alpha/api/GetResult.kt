package com.example.challenge_alpha.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

object GetResult {

    fun <T> suggestionsLiveData(
        networkCall: suspend () -> Result<T>
    ): LiveData<Result<T>> =
        liveData(Dispatchers.IO) {
            emit(Result.loading())

            val response = networkCall.invoke()
            if (response.status == Result.Status.SUCCESS) {
                emit(Result.success(response.data!!))
            } else {
                emit(Result.error<T>(response.message!!))
            }

        }


    fun <T> resultLiveData(
        networkCall: suspend () -> Result<T>,
        saveSearched: suspend (T) -> Unit
    ): LiveData<Result<T>> =
        liveData(Dispatchers.IO) {
            emit(Result.loading())

            val response = networkCall.invoke()
            if (response.status == Result.Status.SUCCESS) {
                saveSearched(response.data!!)
                emit(Result.success(response.data))

            } else {
                emit(Result.error<T>(response.message!!))
            }

        }


    suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        return Result.error("Network call has failed for a following reason: $message")
    }

}