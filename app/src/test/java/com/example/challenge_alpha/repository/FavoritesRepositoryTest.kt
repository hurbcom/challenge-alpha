package com.example.challenge_alpha.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.data.favorites.FavoritesDao
import com.example.challenge_alpha.data.favorites.FavoritesDataBase
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities
import com.example.challenge_alpha.model.ResultDetailGallery
import com.example.challenge_alpha.model.ResultDetailTaxes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*

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

        favoritesRepository.isFavorited("123")

    }

    @Test
    fun getFavorites() {

        favoritesRepository.getFavorites()


    }

    @Test
    fun deleteFavorite() {
        runBlockingTest {
            favoritesRepository.deleteFavorite("123")
        }
    }

    @Test
    fun insertFavorite() {

        val result = ResultDetailRelation()
        result.resultDetail = ResultDetail("123")
        result.resultDetailAmenities = listOf(ResultDetailAmenities("123"))
        result.resultDetailGallery = listOf(ResultDetailGallery("123"))
        result.resultDetailTaxes = listOf(ResultDetailTaxes("123"))
        runBlockingTest {
            favoritesRepository.insertFavorite(result)
        }

    }
}