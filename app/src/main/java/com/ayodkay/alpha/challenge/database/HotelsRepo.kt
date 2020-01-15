package com.ayodkay.alpha.challenge.database

import androidx.lifecycle.LiveData
import org.json.JSONObject

class HotelsRepo(private val hotelsDao: HotelsDao) {

    val allHotels: LiveData<List<Hotels>> = hotelsDao.getAll()

    suspend fun insert(hotels: List<Hotels>){
        hotelsDao.insert(hotels)
    }

    suspend fun nukeAllTable(){
        hotelsDao.nukeTable()
    }
}