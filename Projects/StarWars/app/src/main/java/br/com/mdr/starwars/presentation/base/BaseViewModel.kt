package br.com.mdr.starwars.presentation.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    abstract suspend fun refresh()
}