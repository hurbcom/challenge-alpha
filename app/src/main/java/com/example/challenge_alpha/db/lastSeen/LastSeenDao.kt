package com.example.challenge_alpha.db.lastSeen

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.challenge_alpha.db.ResultDetailRelation
import com.example.challenge_alpha.model.*

@Dao
interface LastSeenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(resultDetail: ResultDetail)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAmenities(resultAmenities: List<ResultDetailAmenities>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGallery(resultDetailGallery: List<ResultDetailGallery>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaxes(resultDetailTaxes: List<ResultDetailTaxes>)

    @Transaction
    @Query("SELECT * FROM ResultDetail ORDER BY timestamp DESC")
    fun getAllDesc(): LiveData<List<ResultDetailRelation>>

    @Transaction
    @Query("SELECT * FROM ResultDetail ORDER BY RANDOM() LIMIT 15")
    fun getAllRandom(): LiveData<List<ResultDetailRelation>>
}