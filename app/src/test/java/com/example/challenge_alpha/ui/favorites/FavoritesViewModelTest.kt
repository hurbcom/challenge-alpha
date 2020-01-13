package com.example.challenge_alpha.ui.favorites



import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challenge_alpha.di.viewModel
import com.example.challenge_alpha.repository.FavoritesRepository
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class FavoritesViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val favoritesRepository = mock(FavoritesRepository::class.java)
    private lateinit var favoriteViewModel: FavoritesViewModel


    @Before
    fun before() {
        favoriteViewModel = FavoritesViewModel(favoritesRepository)
    }

    @Test
    fun getFavorites() {

        verify(favoritesRepository).getFavorites()
        verifyNoMoreInteractions(favoritesRepository)



    }
}