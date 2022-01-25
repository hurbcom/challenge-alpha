package com.br.natanbrito.challenge.domain.usecases

import com.br.natanbrito.challenge.data.repository.HotelRepositoryImpl
import com.br.natanbrito.challenge.data.model.Hotel
import javax.inject.Inject


class GetHotelsUseCaseImpl @Inject constructor(
    private val hotelRepository: HotelRepositoryImpl
): GetHotelsUseCase {

    override suspend fun invoke(): Hotel? = hotelRepository.getHotels()

}