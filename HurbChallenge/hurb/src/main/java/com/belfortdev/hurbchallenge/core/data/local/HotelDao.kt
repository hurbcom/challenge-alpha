package com.belfortdev.hurbchallenge.core.data.local

import android.arch.persistence.room.*
import com.belfortdev.hurbchallenge.core.model.SearchEntity
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