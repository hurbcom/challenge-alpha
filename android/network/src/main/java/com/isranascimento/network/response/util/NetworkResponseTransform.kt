package com.isranascimento.network.response.util

import com.isranascimento.network.response.NetworkResponse
import retrofit2.Response

inline fun <T> withNetworkResponse(networkCall: () -> Response<T>): NetworkResponse<T> {
    try {
        val response = networkCall()
        if(response.isSuccessful) {
            return NetworkResponse.Success(response)
        }
        // Se tivesse tratamento para erros específicos, provavelmente entraria aqui algo como
        // NetworkResponse.HttpError(code: Int, body: ???)
        return NetworkResponse.GenericError()
    } catch (e: Exception) {
        e.printStackTrace()
        return NetworkResponse.GenericError()
    }
}