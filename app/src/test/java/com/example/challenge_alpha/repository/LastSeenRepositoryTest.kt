package com.example.challenge_alpha.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.data.lastSeen.LastSeenDao
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities
import com.example.challenge_alpha.model.ResultDetailGallery
import com.example.challenge_alpha.model.ResultDetailTaxes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LastSeenRepositoryTest {


    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val lastSeenDao = mock(LastSeenDao::class.java)

    private lateinit var lastSeenRepository: LastSeenRepository


    @Before
    fun setUp() {
        lastSeenRepository = LastSeenRepository(lastSeenDao)
    }

    @Test
    fun getLastSeen() {
        lastSeenRepository.getLastSeen()
        verify(lastSeenDao).getAllDesc()
        verifyNoMoreInteractions(lastSeenDao)

    }

    @Test
    fun insertDetailRelation() {

        val fakeResult = ResultDetailRelation().apply {
            resultDetail = ResultDetail("123")
            resultDetailAmenities = listOf(ResultDetailAmenities("123"))
            resultDetailGallery = listOf(ResultDetailGallery("123"))
            resultDetailTaxes = listOf(ResultDetailTaxes("123"))
        }
        runBlockingTest {
            lastSeenRepository.insertDetailRelation(fakeResult)
            verify(lastSeenDao).insertDetail(fakeResult.resultDetail!!)
            verify(lastSeenDao).insertAmenities(fakeResult.resultDetailAmenities)
            verify(lastSeenDao).insertGallery(fakeResult.resultDetailGallery)
            verify(lastSeenDao).insertTaxes(fakeResult.resultDetailTaxes)
        }


        verifyNoMoreInteractions(lastSeenDao)

    }
}