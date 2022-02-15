package com.isranascimento.hotels.repository

import com.isranascimento.hotels.mapper.asDomainModel
import com.isranascimento.core.models.hotel.Hotel
import com.isranascimento.hotels.models.HotelsListDomainState
import com.isranascimento.network.response.NetworkResponse
import com.isranascimento.network.service.IHotelsRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HotelsListRepository(
    private val remoteDataSource: IHotelsRemoteDataSource
) : IHotelsListRepository {
    private var hotelsDomainListMap = mapOf<HotelId, Hotel>()

    override suspend fun getHotelList(): HotelsListDomainState = withContext(Dispatchers.IO) {
        when(val response = remoteDataSource.getHotelsList()) {
            is NetworkResponse.Success -> {
                response.body!!.asDomainModel().let {
                    prepareHotelListMap(it)
                    return@withContext HotelsListDomainState.Success(it)
                }
            }
            is NetworkResponse.GenericError -> {
                return@withContext HotelsListDomainState.Error
            }
        }
    }

    private fun prepareHotelListMap(it: List<Hotel>) {
        hotelsDomainListMap = it.associateBy { hotel -> hotel.id }
    }

    override fun getHotelWithId(hotelId: String): Hotel? {
        return hotelsDomainListMap[hotelId]
    }
}

typealias HotelId = String