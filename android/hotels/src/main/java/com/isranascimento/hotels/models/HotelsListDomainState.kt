package com.isranascimento.hotels.models

import com.isranascimento.core.models.hotel.Hotel

sealed class HotelsListDomainState {
    data class Success(
        val hotelList: List<Hotel>
    ): HotelsListDomainState()

    object Error : HotelsListDomainState()
}