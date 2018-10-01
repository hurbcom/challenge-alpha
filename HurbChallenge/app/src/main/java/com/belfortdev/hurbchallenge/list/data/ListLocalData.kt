package com.example.belfortdev.modernandroid.list.data

import com.belfortdev.hurbchallenge.core.network.Scheduler
import com.example.belfortdev.modernandroid.core.data.local.HotelDatabase
import com.example.belfortdev.modernandroid.core.extension.performOnBack
import com.example.belfortdev.modernandroid.core.model.SearchDomain
import com.example.belfortdev.modernandroid.core.model.SearchEntity
import io.reactivex.Completable
import io.reactivex.Flowable

class ListLocalData(private val hotelDB: HotelDatabase, private val scheduler: Scheduler) : ListDataContract.Local {

    override fun getHotels(): Flowable<List<SearchDomain.Hotel>> {
        return hotelDB.hotelDao().getAll().map {
            convertEntityToDomain(it)
        }
    }

    override fun saveHotels(hotels: List<SearchDomain.Hotel>) {
        Completable.fromAction {
            val entityHotels = convertDomainToEntity(hotels)
            hotelDB.hotelDao().upsertAll(entityHotels)
        }
                .performOnBack(scheduler)
                .subscribe()
    }

    private fun convertDomainToEntity(domainList: List<SearchDomain.Hotel>): List<SearchEntity.Hotel> {
        return domainList.map { hotel ->
            SearchEntity.Hotel(hotel)
        }
    }

    private fun convertEntityToDomain(entityList: List<SearchEntity.Hotel>): List<SearchDomain.Hotel> {
        return entityList.map { hotelEntity ->
            SearchDomain.Hotel(hotelEntity)
        }
    }

}