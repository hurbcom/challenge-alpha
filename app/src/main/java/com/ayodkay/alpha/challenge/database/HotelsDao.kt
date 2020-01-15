package com.ayodkay.alpha.challenge.database

import androidx.lifecycle.LiveData
import androidx.room.*
import org.json.JSONObject

@Dao
interface HotelsDao {

    @Query("SELECT * FROM hotel_table")
    fun getAll(): LiveData<List<Hotels>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(hotels: List<Hotels>)

    @Query("DELETE FROM hotel_table")
    suspend fun nukeTable()

}