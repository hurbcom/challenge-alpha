package com.example.challenge_alpha.repository

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challenge_alpha.api.HurbResponse
import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.data.lastSeen.LastSeenDao
import com.example.challenge_alpha.data.resultDetail.ResultDetailDao
import com.example.challenge_alpha.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ResultDetailRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val resultDetailDao = Mockito.mock(ResultDetailDao::class.java)

    private lateinit var resultDetailRepository: ResultDetailRepository


    @Before
    fun setUp() {
        resultDetailRepository = ResultDetailRepository(resultDetailDao)
    }

    @Test
    fun getResult() {
        resultDetailRepository.getResult("123")
        verify(resultDetailDao).getResult("123")
        Mockito.verifyNoMoreInteractions(resultDetailDao)
    }

    @Test
    fun getSearch() {
        resultDetailRepository.getSearch()
        verify(resultDetailDao).getAllRandom()
        Mockito.verifyNoMoreInteractions(resultDetailDao)
    }

    @Test
    fun insertDetail() {

        val fake = HurbResponse(
            Header(),
            Filters(),
            listOf(ResultDetail(sku = "123",
                price = ResultDetailPrice(123f).apply {
                    this.taxes = listOf(ResultDetailTaxes("123"))
                }).apply {
                this.amenities = listOf(ResultDetailAmenities("123"))
                this.gallery = listOf(ResultDetailGallery("123"))
            }),
            Pagination()
        )


        runBlockingTest {
            resultDetailRepository.insertDetail(fake)
            fake.resultDetail.forEach {
                verify(resultDetailDao).insertDetail(it)
                verify(resultDetailDao).insertAmenities(it.amenities!!)
                verify(resultDetailDao).insertGallery(it.gallery!!)
                verify(resultDetailDao).insertTaxes(it.price?.taxes!!)

            }


        }

    }
}