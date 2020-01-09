package com.example.challenge_alpha.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.challenge_alpha.api.*
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "HurbCall"

@Singleton
class HurbRepository @Inject constructor(
    private val call: HurbService,
    private val save: ResultDetailRepository
) {
    private var lastRequestedPage = 1
    private var query = ""

    fun search(queryString: String) {
        lastRequestedPage++
        query = queryString

    }

    val searchResult = resultLiveData(
        networkCall = { getResult { call.searchRepos(query, lastRequestedPage) } },
        saveSearched = { save.insertDetail(it) }
    )


    private fun <T> resultLiveData(
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


    private suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
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