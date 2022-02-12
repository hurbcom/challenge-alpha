package com.isranascimento.network.service

import com.isranascimento.datatransferobjects.hotels.HotelsResponse
import com.isranascimento.network.response.NetworkResponse

interface IHotelsRemoteDataSource {
    suspend fun getHotelsList(): NetworkResponse<HotelsResponse>
}
