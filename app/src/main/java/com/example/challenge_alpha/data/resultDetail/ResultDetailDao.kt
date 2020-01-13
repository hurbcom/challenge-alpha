package com.example.challenge_alpha.data.resultDetail

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.model.*

@Dao
interface ResultDetailDao {


    @Transaction
    @Query("SELECT * FROM ResultDetail WHERE sku = :sku")
    fun getResult(sku: String): LiveData<ResultDetailRelation>

    @Query("SELECT sku FROM ResultDetail WHERE sku = :sku")
    suspend fun isFavorited(sku: String): String?

    @Query("DELETE FROM ResultDetail WHERE sku = :sku")
    suspend fun deleteFavorited(sku: String)

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