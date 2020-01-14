package com.ayodkay.alpha.challenge.database

import androidx.lifecycle.LiveData

class HotelsRepo(private val hotelsDao: HotelsDao) {

    val allHotels: LiveData<List<Hotels>> = hotelsDao.getAll()

    suspend fun insert(hotels: Hotels){
        hotelsDao.insert(hotels)
    }

    suspend fun nukeAllTable(){
        hotelsDao.nukeTable()
    }
}