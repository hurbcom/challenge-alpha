package com.br.natanbrito.challenge.domain.usecases

import com.br.natanbrito.challenge.data.model.HotelNetworkResult
import com.br.natanbrito.challenge.data.repository.HotelRepositoryImpl
import javax.inject.Inject

class GetHotelsUseCaseImpl @Inject constructor(
    private val hotelRepository: HotelRepositoryImpl
) : GetHotelsUseCase {

    override suspend fun invoke(): HotelNetworkResult = hotelRepository.getHotels()
}
