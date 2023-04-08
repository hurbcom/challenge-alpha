package com.example.hurbandroidchallenge.data.remote.util

import com.example.hurbandroidchallenge.commom.model.Message
import com.google.gson.Gson
import kotlinx.coroutines.coroutineScope
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import br.com.simpledex.commom.model.Result

internal suspend fun <T : Any> apiCall(call: suspend () -> T): T {
    return coroutineScope {
        try {
            call()
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    Result.Error(Result.Error.Type.NETWORK)
                    throw Throwable("Ocorreu um problema de conexão")
                }
                is HttpException -> {
                    val code = throwable.code()
                    val message = convertErrorBody(throwable)
                    Result.Error(Result.Error.Type.HTTP, code, message)
                    throw Throwable(message?.error?.message)
                }
                else -> {
                    Result.Error(Result.Error.Type.GENERIC)
                    throw throwable
                }
            }
        }
    }
}


private fun convertErrorBody(throwable: HttpException): Message? {
    return try {
        val json: String = throwable.response()?.errorBody()?.string() ?: ""
        val jsonObject = JSONObject(json)
        Gson().fromJson(jsonObject.toString(), Message::class.java)
    } catch (throwable: Throwable) {
        return null
    }
}