package com.example.challenge_alpha.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities

@Dao
interface ResultDetailDao {


    @Transaction
    @Query("SELECT * FROM ResultDetail WHERE sku = :sku")
    suspend fun getResult(sku: String): ResultDetailRelation

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(resultDetail: ResultDetail)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAmenities(resultAmenities: List<ResultDetailAmenities>)
}