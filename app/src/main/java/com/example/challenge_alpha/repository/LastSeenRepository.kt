package com.example.challenge_alpha.repository

import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.data.lastSeen.LastSeenDao
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * classe responsável pela obtenção/inserção de últimos vistos
 */
@Singleton
class LastSeenRepository @Inject constructor(private val lastSeenDao: LastSeenDao) {

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