package com.isranascimento.network.service

import com.isranascimento.network.response.util.withNetworkResponse
import com.isranascimento.network.restclient.HurbRestClient

class HurbApiService(
    private val client: HurbRestClient
): IHotelsRemoteDataSource {
    override suspend fun getHotelsList() = withNetworkResponse {
        client.getHotelsList()
    }
}