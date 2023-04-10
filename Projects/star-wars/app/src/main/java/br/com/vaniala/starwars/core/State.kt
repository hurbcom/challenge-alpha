package br.com.vaniala.starwars.core

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
sealed class State<out T : Any> {
    object Loading : State<Nothing>()
    object Empty : State<Nothing>()
    data class Success<out T : Any>(val result: T) : State<T>()
    data class Error(val message: String) : State<Nothing>()
}
