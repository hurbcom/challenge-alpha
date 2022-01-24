package com.br.natanbrito.challenge.domain.model

sealed class HotelNetworkResult {
    class Success(val hotel: Hotel): HotelNetworkResult()
    class Error(val message: String?): HotelNetworkResult()
}
