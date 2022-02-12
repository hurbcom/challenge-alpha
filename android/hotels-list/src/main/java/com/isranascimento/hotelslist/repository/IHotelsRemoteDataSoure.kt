package com.isranascimento.hotelslist.repository

import com.isranascimento.datatransferobjects.hotels.HotelsResponse
import com.isranascimento.network.response.NetworkResponse

interface IHotelsRemoteDataSoure {
    suspend fun getHotelsList(): NetworkResponse<HotelsResponse>
}
