package com.example.challenge_alpha.ui.resultDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities
import com.example.challenge_alpha.model.ResultDetailGallery
import com.example.challenge_alpha.model.ResultDetailTaxes
import com.example.challenge_alpha.repository.FavoritesRepository
import com.example.challenge_alpha.repository.LastSeenRepository
import com.example.challenge_alpha.repository.ResultDetailRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ResultDetailViewModelTest {


    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val lastSeenRepository: LastSeenRepository =
        mock(LastSeenRepository::class.java)
    private val resultDetailRepository: ResultDetailRepository =
        mock(ResultDetailRepository::class.java)
    private val favoritesRepository: FavoritesRepository =
        mock(FavoritesRepository::class.java)
    private lateinit var resultDetailViewModel: ResultDetailViewModel

    private val sku = "buzios"

    @Before
    fun before() {
        resultDetailViewModel = ResultDetailViewModel(
            lastSeenRepository,
            favoritesRepository,
            resultDetailRepository,
            sku
        )

        Dispatchers.setMain(mainThreadSurrogate)

    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()

    }


    private fun insertReturn() {

        val result = MutableLiveData(ResultDetailRelation().apply {
            resultDetail = ResultDetail(sku)
            resultDetailAmenities = listOf(ResultDetailAmenities(sku))
            resultDetailGallery = listOf(ResultDetailGallery(sku))
            resultDetailTaxes = listOf(ResultDetailTaxes(sku))
        })
        val liveResult: LiveData<ResultDetailRelation> = result

        val favorite = MutableLiveData(ResultDetail(sku))
        val liveFavorite: LiveData<ResultDetail?> = favorite

        `when`(resultDetailRepository.getResult(sku)).thenReturn(liveResult)
        `when`(favoritesRepository.isFavorited(sku)).thenReturn(liveFavorite)
    }


    @Test
    fun getResult() {

        insertReturn()

        resultDetailViewModel.getResult().observeForever {
            runBlocking {
                launch(Dispatchers.Main) {
                    verify(lastSeenRepository).insertDetailRelation(it)
                }
            }
            Assert.assertEquals(sku, it.resultDetail?.sku)
            Assert.assertEquals(sku, it.resultDetailTaxes.firstOrNull()?.sku)
            Assert.assertEquals(sku, it.resultDetailGallery.firstOrNull()?.sku)
            Assert.assertEquals(sku, it.resultDetailAmenities.firstOrNull()?.sku)
        }

        verify(resultDetailRepository).getResult(sku)
        verifyNoMoreInteractions(lastSeenRepository)
        verifyNoMoreInteractions(resultDetailRepository)

    }

    @Test
    fun isFavorited() {

        insertReturn()

        resultDetailViewModel.isFavorited().observeForever {
            Assert.assertEquals(true, it)
        }

        verify(favoritesRepository).isFavorited(sku)

    }

    @Test
    fun insertFavorite() {

        insertReturn()

        resultDetailViewModel.insertFavorite()
        resultDetailViewModel.getResult().observeForever {
            runBlockingTest {
                verify(favoritesRepository).insertFavorite(it)
            }
        }


    }

    @Test
    fun deleteFavorite() {

        insertReturn()

        resultDetailViewModel.deleteFavorite()
        resultDetailViewModel.getResult().observeForever {
            runBlocking {

                verify(favoritesRepository).deleteFavorite(it.resultDetail?.sku!!)
            }
        }

    }
}