package com.example.challenge_alpha.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.data.favorites.FavoritesDao
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities
import com.example.challenge_alpha.model.ResultDetailGallery
import com.example.challenge_alpha.model.ResultDetailTaxes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FavoritesRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val favoritesDao = mock(FavoritesDao::class.java)

    private lateinit var favoritesRepository: FavoritesRepository


    @Before
    fun setUp() {
        favoritesRepository = FavoritesRepository(favoritesDao)
    }


    @Test
    fun isFavorited() {

        `when`(favoritesDao.isFavorited("123")).thenReturn(MutableLiveData())

        Assert.assertEquals(null, favoritesRepository.isFavorited("123").value)

        val favorite = MutableLiveData(ResultDetail("123"))
        val liveFavorite: LiveData<ResultDetail?> = favorite
        `when`(favoritesDao.isFavorited("123")).thenReturn(liveFavorite)

        Assert.assertEquals(ResultDetail("123"), favoritesRepository.isFavorited("123").value)

        verify(favoritesDao, times(2)).isFavorited("123")
        verifyNoMoreInteractions(favoritesDao)

    }

    @Test
    fun getFavorites() {

        `when`(favoritesDao.getAllDesc()).thenReturn(MutableLiveData())

        Assert.assertEquals(null, favoritesRepository.getFavorites().value)

        val favorite = MutableLiveData(
            listOf(
                ResultDetailRelation().apply {
                    resultDetail = ResultDetail("123", timestamp = Date(50))
                    resultDetailAmenities = listOf(ResultDetailAmenities("123"))
                    resultDetailGallery = listOf(ResultDetailGallery("123"))
                    resultDetailTaxes = listOf(ResultDetailTaxes("123"))
                },
                ResultDetailRelation().apply {
                    resultDetail = ResultDetail("abc", timestamp = Date(100))
                    resultDetailAmenities = listOf(ResultDetailAmenities("abc"))
                    resultDetailGallery = listOf(ResultDetailGallery("abc"))
                    resultDetailTaxes = listOf(ResultDetailTaxes("abc"))
                }).sortedByDescending { it.resultDetail?.timestamp }
        )
        val liveFavorite: LiveData<List<ResultDetailRelation>> = favorite
        `when`(favoritesDao.getAllDesc()).thenReturn(liveFavorite)

        Assert.assertEquals("abc", favoritesRepository.getFavorites().value?.firstOrNull()?.resultDetail?.sku)
        Assert.assertEquals(2, favoritesRepository.getFavorites().value?.size)

        verify(favoritesDao, times(3)).getAllDesc()
        verifyNoMoreInteractions(favoritesDao)

    }

    @Test
    fun deleteFavorite() {

        runBlockingTest {
            favoritesRepository.deleteFavorite("123")
            verify(favoritesDao).deleteFavorited("123")
        }

        verifyNoMoreInteractions(favoritesDao)
    }

    @Test
    fun insertFavorite() {

        val result = ResultDetailRelation().apply {
            resultDetail = ResultDetail("123")
            resultDetailAmenities = listOf(ResultDetailAmenities("123"))
            resultDetailGallery = listOf(ResultDetailGallery("123"))
            resultDetailTaxes = listOf(ResultDetailTaxes("123"))
        }
        runBlockingTest {
            favoritesRepository.insertFavorite(result)
            verify(favoritesDao).insertDetail(result.resultDetail!!)
            verify(favoritesDao).insertAmenities(result.resultDetailAmenities)
            verify(favoritesDao).insertGallery(result.resultDetailGallery)
            verify(favoritesDao).insertTaxes(result.resultDetailTaxes)
        }


        verifyNoMoreInteractions(favoritesDao)

    }
}