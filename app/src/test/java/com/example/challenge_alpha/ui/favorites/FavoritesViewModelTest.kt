package com.example.challenge_alpha.ui.favorites


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.di.viewModel
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities
import com.example.challenge_alpha.model.ResultDetailGallery
import com.example.challenge_alpha.model.ResultDetailTaxes
import com.example.challenge_alpha.repository.FavoritesRepository
import com.example.challenge_alpha.util.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*


@RunWith(JUnit4::class)
class FavoritesViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()


    private val favoritesRepository: FavoritesRepository = mock(FavoritesRepository::class.java)
    private lateinit var favoritesViewModel: FavoritesViewModel


    @Before
    fun before() {
        favoritesViewModel = FavoritesViewModel(favoritesRepository)

    }

    private fun insertFavorite() {

        val favorite = MutableLiveData(listOf(ResultDetailRelation().apply {
            resultDetail = ResultDetail("123")
            resultDetailAmenities = listOf(ResultDetailAmenities("123"))
            resultDetailGallery = listOf(ResultDetailGallery("123"))
            resultDetailTaxes = listOf(ResultDetailTaxes("123"))
        }))
        val liveFavorite: LiveData<List<ResultDetailRelation>> = favorite

        `when`(favoritesRepository.getFavorites()).thenReturn(liveFavorite)

    }

    @Test
    fun getFavorites() {

        favoritesViewModel.getFavorites()

        verify(favoritesRepository).getFavorites()
        verifyNoMoreInteractions(favoritesRepository)

    }

}