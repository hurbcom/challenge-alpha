package com.isranascimento.hotels.ui.models

sealed class HotelListUIState {
    object Loading: HotelListUIState()
    object Error: HotelListUIState()
    data class Success(
        val hotelsValue: List<HotelListUI>
    ): HotelListUIState()
}