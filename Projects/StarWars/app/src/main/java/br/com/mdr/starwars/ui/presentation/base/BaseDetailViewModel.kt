package br.com.mdr.starwars.ui.presentation.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseDetailViewModel : BaseViewModel() {
    protected val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite
    abstract fun setFavorite(isFavorite: Boolean)
}
