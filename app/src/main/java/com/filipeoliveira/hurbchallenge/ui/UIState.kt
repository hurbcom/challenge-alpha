package com.filipeoliveira.hurbchallenge.ui

import androidx.annotation.StringRes

sealed class UIState<out T> {
    object Loading: UIState<Nothing>()
    data class Error(@StringRes val message: Int): UIState<Nothing>()
    data class Success<T>(val data: T): UIState<T>()
    object Idle: UIState<Nothing>()
}
