package com.example.challenge_alpha.repository

import com.example.challenge_alpha.api.HurbResponse
import com.example.challenge_alpha.db.ResultDetailRelation
import com.example.challenge_alpha.db.favorites.FavoritesDao
import com.example.challenge_alpha.model.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepository @Inject constructor(private val favoritesDao: FavoritesDao) {


    fun isFavorited(sku: String) = favoritesDao.isFavorited(sku)

    fun getFavorites() = favoritesDao.getAllDesc()

    suspend fun deleteFavorite(sku: String) {

        favoritesDao.deleteFavorited(sku)
    }

    suspend fun insertFavorite(detailRelation: ResultDetailRelation) {

        favoritesDao.insertDetail(detailRelation.resultDetail.apply {
            this?.timestamp = Calendar.getInstance().time
        }!!)
        favoritesDao.insertAmenities(detailRelation.resultDetailAmenities)
        favoritesDao.insertGallery(detailRelation.resultDetailGallery)
        favoritesDao.insertTaxes(detailRelation.resultDetailTaxes)
    }

}