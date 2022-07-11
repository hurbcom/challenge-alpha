package com.edufelip.challengealpha.presentation.fragments.general_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.edufelip.challengealpha.MainCoroutineRule
import com.edufelip.challengealpha.data.repositories.GeneralListMenuFakeRepository
import com.edufelip.challengealpha.domain.usecases.GetGeneralListMenuItemsUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class GeneralListMenuViewModelTest {
    private lateinit var viewModel: GeneralListMenuViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        viewModel = GeneralListMenuViewModel(
            GetGeneralListMenuItemsUseCase(GeneralListMenuFakeRepository())
        )
    }

    @Test
    fun `Should retrieve main menu from json`() {
        viewModel.getMenuList(context = ApplicationProvider.getApplicationContext())
        assertThat(viewModel.generalListMenuItemsState.value).isEqualTo("")
    }
}