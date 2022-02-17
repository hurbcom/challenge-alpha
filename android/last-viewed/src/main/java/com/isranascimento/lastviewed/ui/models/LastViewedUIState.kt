package com.isranascimento.lastviewed.ui.models

import com.isranascimento.theme.hotel.HotelCardItem

sealed class LastViewedUIState {
    object Empty: LastViewedUIState()
    data class WithItem(
        val items: List<HotelCardItem>
    ): LastViewedUIState()
}