package com.example.belfortdev.modernandroid.core.data.local

import android.arch.persistence.room.*
import com.example.belfortdev.modernandroid.core.model.SearchEntity
import io.reactivex.Flowable

/**
 * Created by belfortdev on 27/09/18.
 */

@Dao
interface HotelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertAll(hotels: List<SearchEntity.Hotel>)

    @Query("SELECT * FROM hotel")
    fun getAll(): Flowable<List<SearchEntity.Hotel>>

    @Delete
    fun delete(hotel: SearchEntity.Hotel)

}