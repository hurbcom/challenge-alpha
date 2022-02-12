package com.isranascimento.hotelslist.repository

import com.isranascimento.hotelslist.mapper.asDomainModel
import com.isranascimento.hotelslist.models.Hotel
import com.isranascimento.hotelslist.models.HotelsListDomainState
import com.isranascimento.network.response.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HotelsListRepository(
    private val remoteDataSource: IHotelsRemoteDataSoure
) : IHotelsListRepository {
    private var hotelsDomainListMap = mapOf<HotelId, Hotel>()

    override suspend fun getHotelList(): HotelsListDomainState = withContext(Dispatchers.IO) {
        when(val response = remoteDataSource.getHotelsList()) {
            is NetworkResponse.Success -> {
                response.body?.asDomainModel()?.let {
                    prepareHotelListMap(it)
                    return@withContext HotelsListDomainState.Success(it)
                } ?: return@withContext HotelsListDomainState.Error
            }
            is NetworkResponse.GenericError -> {
                return@withContext HotelsListDomainState.Error
            }
        }
    }

    private fun prepareHotelListMap(it: List<Hotel>) {
        hotelsDomainListMap = it.associateBy { hotel -> hotel.id }
    }

    override fun getHotelWithSku(hotelId: String): Hotel? {
        return hotelsDomainListMap[hotelId]
    }
}

typealias HotelId = String