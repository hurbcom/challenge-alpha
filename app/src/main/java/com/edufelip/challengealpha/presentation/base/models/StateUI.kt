package com.edufelip.challengealpha.presentation.base.models

sealed class StateUI<out T : Any> {
    data class Idle(val message: String = "") : StateUI<Nothing>()
    data class Processed<out T : Any>(val data: T) : StateUI<T>()
    data class Processing(val message: String = "") : StateUI<Nothing>()
    data class Error(val message: String = "", val info: Any? = null) : StateUI<Nothing>()

    fun idle() = this is Idle
    fun loading() = this is Processing
    fun processed() = this is Processed
    fun error() = this is Error
}