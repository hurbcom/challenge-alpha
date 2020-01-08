package com.example.challenge_alpha.db.favorites

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.challenge_alpha.db.ResultDetailRelation
import com.example.challenge_alpha.model.*

@Dao
interface FavoritesDao {

    @Transaction
    @Query("SELECT * FROM ResultDetail ORDER BY timestamp DESC")
    fun getAllDesc(): LiveData<List<ResultDetailRelation>>

    @Query("SELECT * FROM ResultDetail WHERE sku = :sku")
    fun isFavorited(sku: String): LiveData<ResultDetail?>

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

}