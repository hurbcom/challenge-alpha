package com.isranascimento.hotelslist.ui.viewmodels.models

sealed class HotelListUIState {
    object Loading: HotelListUIState()
    object Error: HotelListUIState()
    data class Success(
        val hotelsValue: List<HotelListUI>
    ): HotelListUIState()
}