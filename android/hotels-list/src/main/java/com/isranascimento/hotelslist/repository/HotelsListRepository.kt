package com.isranascimento.hotelslist.repository

import com.isranascimento.hotelslist.mapper.asDomainModel
import com.isranascimento.hotelslist.models.Hotel
import com.isranascimento.hotelslist.models.HotelsListDomainState
import com.isranascimento.network.response.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HotelsListRepository(
    private val remoteDataSource: IHotelsRemoteDataSoure
) {
    suspend fun getHotelList(): HotelsListDomainState = withContext(Dispatchers.IO) {
        return@withContext when(val response = remoteDataSource.getHotelsList()) {
            is NetworkResponse.Success -> {
                response.body?.asDomainModel()?.let { HotelsListDomainState.Success(it) } ?: HotelsListDomainState.Error
            }
            is NetworkResponse.GenericError -> {
                HotelsListDomainState.Error
            }
        }
    }
}