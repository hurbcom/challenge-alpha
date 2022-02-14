package com.isranascimento.hotels.models

sealed class HotelsListDomainState {
    data class Success(
        val hotelList: List<Hotel>
    ): HotelsListDomainState()

    object Error : HotelsListDomainState()
}