package com.isranascimento.lastviewed.ui.viewmodel

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.isranascimento.coremodels.hotel.Address
import com.isranascimento.coremodels.hotel.Hotel
import com.isranascimento.lastviewed.repository.ILastViewedRepository
import com.isranascimento.lastviewed.ui.models.LastViewedUIState
import com.isranascimento.lastviewed.ui.viewmodel.LastViewedViewModelTest.LastViewedRepositoryDouble.ExpectedValue.EMPTY
import com.isranascimento.lastviewed.ui.viewmodel.LastViewedViewModelTest.LastViewedRepositoryDouble.ExpectedValue.WITH_ITEMS
import com.isranascimento.testutils.MainCoroutineRule
import com.isranascimento.theme.hotel.HotelCardItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LastViewedViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var sut: LastViewedViewModel

    @Test
    fun `WHEN repository returns empty THEN the viewmodel sets the ui state to EMPTY`() = runBlocking {
        sut = LastViewedViewModel(LastViewedRepositoryDouble(EMPTY))
        sut.uiState.test {
            sut.getLastHotelsViewed()
            assertThat(awaitItem()).isEqualTo(LastViewedUIState.Empty)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `WHEN repository returns success THEN the viewmodel sets the ui state to success with correct values`() = runBlocking {
        sut = LastViewedViewModel(LastViewedRepositoryDouble(WITH_ITEMS))

        sut.uiState.test {
            val firstUIState = awaitItem()
            assertThat(firstUIState).isInstanceOf(LastViewedUIState.Empty::class.java)

            sut.getLastHotelsViewed()
            val uiState = awaitItem()

            assertThat(uiState).isInstanceOf(LastViewedUIState.WithItem::class.java)
            (uiState as LastViewedUIState.WithItem)

            assertThat(uiState.items.size).isEqualTo(1)
            assertThat(uiState.items[0]).isEqualTo(
                HotelCardItem(
                    "id",
                    "name",
                    "mainImage",
                    "city",
                    "state",
                    listOf("amenityItem")
                )
            )

            val nextUI = awaitItem()

            assertThat(nextUI).isInstanceOf(LastViewedUIState.WithItem::class.java)
            (nextUI as LastViewedUIState.WithItem)

            assertThat(nextUI.items.size).isEqualTo(2)
            assertThat(nextUI.items[0]).isEqualTo(
                HotelCardItem(
                    "id",
                    "name",
                    "mainImage",
                    "city",
                    "state",
                    listOf("amenityItem")
                )
            )
        }
    }

    class LastViewedRepositoryDouble(
        private val expectedValue: ExpectedValue
    ): ILastViewedRepository {
        enum class ExpectedValue {
            WITH_ITEMS, EMPTY
        }
        override fun getLastViewed(): Flow<List<Hotel>> {
            if(expectedValue == WITH_ITEMS) {
                return flowOf(
                    listOf(createHotel()),
                    listOf(createHotel(), createHotel())
                )
            }
            return flowOf(
                emptyList()
            )
        }

        private fun createHotel(): Hotel = Hotel(
            "id",
            "name",
            listOf("galleryItem"),
            "mainImage",
            listOf("amenityItem"),
            Address(
                "state",
                "city"
            ),
            3,
            "description",
            "url"
        )
    }
}