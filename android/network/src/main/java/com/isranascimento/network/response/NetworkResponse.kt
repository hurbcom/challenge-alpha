package com.isranascimento.network.response

import retrofit2.Response

sealed class NetworkResponse<T> {

    class Success<T>(response: Response<T>): NetworkResponse<T>() {
        val body = response.body()
    }

    // Para o challenge-alpha, não tem nenhum erro para ser tratado diferente (como mostrar uma mensagem
    // de nenhum resultado encontrado por exemplo), logo todos os erros serão tratados de maneira genérica por hora
    open class GenericError<T> : NetworkResponse<T>()
}