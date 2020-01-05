package com.example.challenge_alpha.repository

import com.example.challenge_alpha.db.ResultDetailDao
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities

class ResultRepository(private val resultDetailDao: ResultDetailDao) {

    suspend fun getResult(sku: String) = resultDetailDao.getResult(sku)

    suspend fun insertDetail(resultDetail: ResultDetail) {
        resultDetailDao.insertDetail(resultDetail)
    }

    suspend fun insertAmenities (resultAmenities: List<ResultDetailAmenities>) {
        resultDetailDao.insertAmenities(resultAmenities)
    }

}