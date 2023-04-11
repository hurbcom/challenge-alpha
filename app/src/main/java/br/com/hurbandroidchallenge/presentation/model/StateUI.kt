package br.com.hurbandroidchallenge.presentation.model

sealed class StateUI<out T : Any> {
    object Idle : StateUI<Nothing>()
    object Processing : StateUI<Nothing>()
    data class Error(val message: String = "") : StateUI<Nothing>()
    object Processed : StateUI<Nothing>()

    fun idle() = this is Idle
    fun loading() = this is Processing
    fun processed() = this is Processed
    fun error() = this is Error
}