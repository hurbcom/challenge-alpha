package com.br.natanbrito.challenge.domain.usecases

import com.br.natanbrito.challenge.data.model.HotelNetworkResult

interface GetHotelsUseCase {
    suspend operator fun invoke(): HotelNetworkResult
}
