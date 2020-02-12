package com.example.challenge_alpha.ui.results

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challenge_alpha.api.HurbResponse
import com.example.challenge_alpha.api.Result
import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities
import com.example.challenge_alpha.model.ResultDetailGallery
import com.example.challenge_alpha.model.ResultDetailTaxes
import com.example.challenge_alpha.repository.FavoritesRepository
import com.example.challenge_alpha.repository.HurbRepository
import com.example.challenge_alpha.repository.LastSeenRepository
import com.example.challenge_alpha.repository.ResultDetailRepository
import com.example.challenge_alpha.ui.resultDetail.ResultDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@RunWith(JUnit4::class)
class ResultsViewModelTest {


    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val hurbRepository: HurbRepository =
        Mockito.mock(HurbRepository::class.java)
    private lateinit var resultsViewModel: ResultsViewModel

    private val sku = "buzios"

    @Before
    fun before() {
        resultsViewModel = ResultsViewModel(
            hurbRepository,
            sku
        )
    }




    private fun insertReturn() {

        val result =
            MutableLiveData(Result.success(HurbResponse(resultDetail = listOf(ResultDetail(sku)))))
        val liveResult: LiveData<Result<HurbResponse>> = result

        Mockito.`when`(hurbRepository.searchResult()).thenReturn(liveResult)
    }

    @Test
    fun getResultsDetailLive() {

        insertReturn()

        resultsViewModel.resultsDetailLive()
        verify(hurbRepository).searchResult()
    }


}