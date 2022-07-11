package com.edufelip.challengealpha.presentation.fragments.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.edufelip.challengealpha.MainCoroutineRule
import com.edufelip.challengealpha.data.repositories.FavoritesFakeRepository
import com.edufelip.challengealpha.domain.usecases.DeleteFavoriteUseCase
import com.edufelip.challengealpha.domain.usecases.GetFavoritesListUseCase
import com.edufelip.challengealpha.presentation.base.models.StateUI
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoritesViewModelTest {
    private lateinit var viewModel: FavoritesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        viewModel = FavoritesViewModel(
            getFavoritesListUseCase = GetFavoritesListUseCase(FavoritesFakeRepository()),
            deleteFavoriteUseCase = DeleteFavoriteUseCase(FavoritesFakeRepository()),
        )
    }

    @Test
    fun `Should retrieve favorites`() {
        viewModel.getFavoritesList()
        Truth.assertThat((viewModel.favoritesStateList.value.getContentIfNotHandled() as StateUI.Processed).data)
            .isNotEmpty()
    }
}