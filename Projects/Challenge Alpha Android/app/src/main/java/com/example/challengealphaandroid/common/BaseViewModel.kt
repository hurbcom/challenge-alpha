package com.example.challengealphaandroid.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S> :
    ViewModel() {

    protected val mutableState by lazy { MutableStateFlow(defaultUiState()) }
    val state: StateFlow<S> by lazy { mutableState.asStateFlow() }

    abstract fun defaultUiState(): S
}