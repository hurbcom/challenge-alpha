package br.com.simpledex.commom.model

import com.example.hurbandroidchallenge.commom.model.Message

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(
        val type: Type,
        val code: Int? = null,
        private val messageError: Message? = null
    ) : Result<Nothing>() {

        enum class Type {
            NETWORK, HTTP, GENERIC
        }

        fun message(): String? = messageError?.error?.message
        fun messageErrorCode(): Int? = messageError?.error?.code

        override fun equals(other: Any?): Boolean {
            if (other is Error)
                return this.type == other.type &&
                        (this.code != null || this.code == other.code) &&
                        this.message().equals(other.message())


            return super.equals(other)
        }
    }
}