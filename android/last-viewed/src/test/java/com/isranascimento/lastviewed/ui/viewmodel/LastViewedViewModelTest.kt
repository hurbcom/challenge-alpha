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
        }
    }

    @Test
    fun `WHEN getHotelWithSku is called THEN the viewmodel returns the expected Hotel`() {
        sut = LastViewedViewModel(LastViewedRepositoryDouble(WITH_ITEMS))

        val hotel = sut.getHotelWithId("1")

        assertThat(hotel.id).isEqualTo("id")
        assertThat(hotel.name).isEqualTo("name")
        assertThat(hotel.gallery.size).isEqualTo(1)
        assertThat(hotel.url).isEqualTo("url")
        assertThat(hotel.mainImage).isEqualTo("mainImage")
        assertThat(hotel.description).isEqualTo("description")
        assertThat(hotel.address.city).isEqualTo("city")
        assertThat(hotel.amenities.size).isEqualTo(1)
        assertThat(hotel.starCount).isEqualTo(3)
        assertThat(hotel.address.state).isEqualTo("state")
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
                    listOf(createHotel())
                )
            }
            return flowOf(
                emptyList()
            )
        }

        override fun getHotelWithId(hotelId: String): Hotel? {
            return createHotel()
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