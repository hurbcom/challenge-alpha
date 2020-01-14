package com.ayodkay.alpha.challenge.database

import androidx.lifecycle.LiveData
import org.json.JSONObject

class HotelsRepo(private val hotelsDao: HotelsDao) {

    val allHotels: LiveData<Hotels> = hotelsDao.getAll()

    suspend fun insert(hotels: Hotels){
        hotelsDao.insert(hotels)
    }

    suspend fun nukeAllTable(){
        hotelsDao.nukeTable()
    }
}