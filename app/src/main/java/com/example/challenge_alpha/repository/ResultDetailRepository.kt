package com.example.challenge_alpha.repository

import com.example.challenge_alpha.api.HurbResponse
import com.example.challenge_alpha.data.resultDetail.ResultDetailDao
import com.example.challenge_alpha.model.*
import com.example.challenge_alpha.testing.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

/**
 * classe responsável pela obtenção/inserção de últimas buscas e armazenamento geral de resultados.
 */
@OpenForTesting
@Singleton
class ResultDetailRepository @Inject constructor(private val resultDetailDao: ResultDetailDao) {

    fun getResult(sku: String) = resultDetailDao.getResult(sku)

    fun getSearch() = resultDetailDao.getAllRandom()

    suspend fun insertDetail(response: HurbResponse) {

        response.resultDetail.forEach {
            resultDetailDao.insertDetail(it)
            resultDetailDao.insertAmenities(it.amenities!!.map { list ->
                ResultDetailAmenities(it.sku, list.name, list.category)
            })
            resultDetailDao.insertGallery(it.gallery!!.map { list ->
                ResultDetailGallery(it.sku, list.description, list.url)
            })
            it.price!!.taxes?.map { list ->
                ResultDetailTaxes(
                    it.sku,
                    list.type,
                    list.name,
                    list.amount,
                    list.originalAmountPerDay,
                    list.currency
                )
            }?.let { it1 -> resultDetailDao.insertTaxes(it1) }
        }

    }

}