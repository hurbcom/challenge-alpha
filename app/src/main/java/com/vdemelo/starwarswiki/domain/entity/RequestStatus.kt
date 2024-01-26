package com.vdemelo.starwarswiki.domain.entity

sealed class RequestStatus<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : RequestStatus<T>(data)
    class Error<T>(data: T? = null, message: String) : RequestStatus<T>(data, message)
}
