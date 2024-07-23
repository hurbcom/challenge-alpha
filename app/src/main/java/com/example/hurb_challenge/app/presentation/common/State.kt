package com.example.hurb_challenge.app.presentation.common

sealed class State<out T>

data object Empty : State<Nothing>()
data object Loading : State<Nothing>()

data class Error(
    val message: String
) : State<Nothing>()

data class Loaded<T>(
    val value: T
) : State<T>()