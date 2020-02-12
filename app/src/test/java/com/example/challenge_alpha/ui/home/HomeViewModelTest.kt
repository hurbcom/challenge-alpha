package com.example.challenge_alpha.ui.home


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities
import com.example.challenge_alpha.model.ResultDetailGallery
import com.example.challenge_alpha.model.ResultDetailTaxes
import com.example.challenge_alpha.repository.HurbRepository
import com.example.challenge_alpha.repository.LastSeenRepository
import com.example.challenge_alpha.repository.ResultDetailRepository
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*


@RunWith(JUnit4::class)
class HomeViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()


    private val lastSeenRepository: LastSeenRepository = mock(LastSeenRepository::class.java)
    private val resultDetailRepository: ResultDetailRepository = mock(ResultDetailRepository::class.java)
    private val hurbRepository: HurbRepository = mock(HurbRepository::class.java)
    private lateinit var homeViewModel: HomeViewModel


    @Before
    fun before() {
        homeViewModel = HomeViewModel(lastSeenRepository, resultDetailRepository, hurbRepository)

    }

    private fun insertReturn() {

        val favorite = MutableLiveData(listOf(ResultDetailRelation().apply {
            resultDetail = ResultDetail("123")
            resultDetailAmenities = listOf(ResultDetailAmenities("123"))
            resultDetailGallery = listOf(ResultDetailGallery("123"))
            resultDetailTaxes = listOf(ResultDetailTaxes("123"))
        }))
        val liveFavorite: LiveData<List<ResultDetailRelation>> = favorite

        `when`(lastSeenRepository.getLastSeen()).thenReturn(liveFavorite)
        `when`(resultDetailRepository.getSearch()).thenReturn(liveFavorite)

    }

    @Test
    fun getlastSeen() {

        homeViewModel.getLastSeen()

        verify(lastSeenRepository).getLastSeen()
        verifyNoMoreInteractions(lastSeenRepository)

    }

    @Test
    fun getLastSearched() {

        homeViewModel.getLastSearched()

        verify(resultDetailRepository).getSearch()
        verifyNoMoreInteractions(resultDetailRepository)

    }

    @Test
    fun getSuggestion() {

        homeViewModel.searchSuggestion("buzios")
        homeViewModel.getSuggetion().observeForever{}

        verify(hurbRepository).suggestionSearch("buzios")
        verifyNoMoreInteractions(hurbRepository)
        verifyNoMoreInteractions(resultDetailRepository)
        verifyNoMoreInteractions(lastSeenRepository)

    }

}