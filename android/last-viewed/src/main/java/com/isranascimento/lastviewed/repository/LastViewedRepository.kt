package com.isranascimento.lastviewed.repository

import com.isranascimento.coremodels.hotel.Address
import com.isranascimento.coremodels.hotel.Hotel
import com.isranascimento.database.IRetrieveHotelList
import com.isranascimento.databasedtos.hotels.HotelsWithAmenitiesEntity
import com.isranascimento.lastviewed.mapper.asDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ILastViewedRepository {
    fun getLastViewed(): Flow<List<Hotel>>
    fun getHotelWithId(hotelId: String): Hotel?
}

class LastViewedRepository(
    private val retrieveHotelList: IRetrieveHotelList
) : ILastViewedRepository {
    private var hotelsDomainListMap = mapOf<HotelId, Hotel>()

    override fun getLastViewed(): Flow<List<Hotel>> {
        return retrieveHotelList.getHotelsWithAmenities().map { list ->
            val listDomain = list.asDomainModel()
            prepareHotelListMap(listDomain)
            return@map listDomain
        }
    }

    // TODO: Pensar em como extrair se um utiliza flow e outro lista
    private fun prepareHotelListMap(it: List<Hotel>) {
        hotelsDomainListMap = it.associateBy { hotel -> hotel.id }
    }

    override fun getHotelWithId(hotelId: String): Hotel? {
        return hotelsDomainListMap[hotelId]
    }
}

typealias HotelId = String