package com.isranascimento.lastviewed.repository

import com.isranascimento.coremodels.hotel.Address
import com.isranascimento.coremodels.hotel.Hotel
import com.isranascimento.database.IRetrieveHotelList
import com.isranascimento.databasedtos.hotels.HotelsWithAmenitiesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ILastViewedRepository {
    fun getLastViewed(): Flow<List<Hotel>>
}

class LastViewedRepository(
    private val retrieveHotelList: IRetrieveHotelList
) : ILastViewedRepository {
    override fun getLastViewed(): Flow<List<Hotel>> {
        return retrieveHotelList.getHotelsWithAmenities().map { list ->
            list.asDomainModel()
        }
    }

    private fun List<HotelsWithAmenitiesEntity>.asDomainModel(): List<Hotel> {
        return this.map {
            Hotel(
                id = it.hotel.id,
                name = it.hotel.name,
                gallery = it.gallery.map { item ->
                    item.value
                },
                mainImage = it.hotel.mainImage,
                amenities = it.amenities.map {  item ->
                    item.value
                },
                address = Address(
                    it.hotel.state,
                    it.hotel.city
                ),
                starCount = it.hotel.starCount,
                description = it.hotel.description,
                url = it.hotel.url
            )
        }
    }
}