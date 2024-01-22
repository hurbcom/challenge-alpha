package com.vdemelo.starwarswiki.domain.entity

sealed class RequestStatus<T>( //TODO onde colocar? qual camada pertence?
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : RequestStatus<T>(data)
    class Error<T>(data: T? = null, message: String) : RequestStatus<T>(data, message)
    class Loading<T>(data: T? = null) : RequestStatus<T>(data)

}
