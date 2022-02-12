package com.isranascimento.hotelslist.ui.viewmodels.models

sealed class UIState {
    object Loading: UIState()
    object Error: UIState()
    data class Success(
        val hotels: HotelUI
    ): UIState()
}