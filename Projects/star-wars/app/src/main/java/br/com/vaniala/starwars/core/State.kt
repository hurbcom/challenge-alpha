package br.com.vaniala.starwars.core

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
sealed class State {
    object Loading : State()
    object Empty : State()
    data class Success<out T : Any>(val result: List<T>) : State()
    data class Error(val message: String) : State()
}
