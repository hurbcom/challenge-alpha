package com.example.challenge_alpha.api

import com.example.challenge_alpha.model.Filters
import com.example.challenge_alpha.model.Header
import com.example.challenge_alpha.model.Pagination
import com.example.challenge_alpha.model.ResultDetail
import com.google.gson.annotations.SerializedName

// Data class para as respostas da API
class HurbResponse (
    @SerializedName("meta") val meta: Header = Header(),
    @SerializedName("filters") val filters: Filters = Filters(),
    @SerializedName("results") val resultDetail: List<ResultDetail>,
    @SerializedName("pagination") val pagination: Pagination = Pagination()
)

data class Result<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Result<T> {
            return Result(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(Status.LOADING, data, null)
        }
    }
}