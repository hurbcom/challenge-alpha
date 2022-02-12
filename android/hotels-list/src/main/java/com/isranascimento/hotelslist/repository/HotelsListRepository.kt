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
    private var hotelsDomainListMap = mapOf<HotelId, Hotel>()

    suspend fun getHotelList(): HotelsListDomainState = withContext(Dispatchers.IO) {
        return@withContext when(val response = remoteDataSource.getHotelsList()) {
            is NetworkResponse.Success -> {
                response.body?.asDomainModel()?.let {
                    prepareHotelListMap(it)
                    HotelsListDomainState.Success(it)
                } ?: HotelsListDomainState.Error
            }
            is NetworkResponse.GenericError -> {
                HotelsListDomainState.Error
            }
        }
    }

    private fun prepareHotelListMap(it: List<Hotel>) {
        hotelsDomainListMap = it.associateBy { hotel -> hotel.id }
    }

    fun getHotelWithSku(hotelId: HotelId): Hotel? {
        return hotelsDomainListMap[hotelId]
    }
}

typealias HotelId = String