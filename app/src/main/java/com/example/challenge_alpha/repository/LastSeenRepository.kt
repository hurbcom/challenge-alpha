package com.example.challenge_alpha.repository

import com.example.challenge_alpha.api.HurbResponse
import com.example.challenge_alpha.db.ResultDetailRelation
import com.example.challenge_alpha.db.favorites.FavoritesDao
import com.example.challenge_alpha.db.lastSeen.LastSeenDao
import com.example.challenge_alpha.model.*
import java.util.*

class LastSeenRepository(private val lastSeenDao: LastSeenDao) {

    fun getLastSeen() = lastSeenDao.getAllDesc()


    suspend fun insertDetailRelation(detailRelation: ResultDetailRelation) {

        lastSeenDao.insertDetail(detailRelation.resultDetail.apply {
            this?.timestamp = Calendar.getInstance().time
        }!!)
        lastSeenDao.insertAmenities(detailRelation.resultDetailAmenities)
        lastSeenDao.insertGallery(detailRelation.resultDetailGallery)
        lastSeenDao.insertTaxes(detailRelation.resultDetailTaxes)
    }

}