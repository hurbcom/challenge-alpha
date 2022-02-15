package com.isranascimento.network.service

import com.isranascimento.networkdtos.hotels.HotelsResponse
import com.isranascimento.network.response.NetworkResponse

interface IHotelsRemoteDataSource {
    suspend fun getHotelsList(): NetworkResponse<HotelsResponse>
}
