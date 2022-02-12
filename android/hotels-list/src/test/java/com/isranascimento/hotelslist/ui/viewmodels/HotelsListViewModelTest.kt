package com.isranascimento.hotelslist.ui.viewmodels

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.isranascimento.hotelslist.models.Hotel
import com.isranascimento.hotelslist.models.HotelsListDomainState
import com.isranascimento.hotelslist.repository.IHotelsListRepository
import com.isranascimento.hotelslist.ui.viewmodels.HotelsListViewModelTest.HotelsListRepositoryDouble.ExpectedResponseStatus.ERROR
import com.isranascimento.hotelslist.ui.viewmodels.HotelsListViewModelTest.HotelsListRepositoryDouble.ExpectedResponseStatus.SUCCESS
import com.isranascimento.hotelslist.ui.viewmodels.models.HotelListUIItem
import com.isranascimento.hotelslist.ui.viewmodels.models.HotelListUIState
import com.isranascimento.hotelslist.ui.viewmodels.models.HotelListUITitle
import com.isranascimento.hotelslist.util.ReturnedValues.HOTEL_DOMAIN_LIST
import com.isranascimento.testutils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class HotelsListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var sut: HotelsListViewModel

    @Test
    fun `WHEN repository returns with success THEN the viewmodel update the uiStateFlow to loading and after UiState_Success expected value`() = runBlocking {
        sut = HotelsListViewModel(HotelsListRepositoryDouble(SUCCESS))
        sut.uiState.test {
            sut.getHotelsList()
            assertThat(awaitItem()).isInstanceOf(HotelListUIState.Loading::class.java)
            val successItem = awaitItem()
            assertThat(successItem).isInstanceOf(HotelListUIState.Success::class.java)
            successItem as HotelListUIState.Success
            assertThat(successItem.hotelsValue.size).isEqualTo(7)
            assertThat(successItem.hotelsValue[0]).isEqualTo(HotelListUITitle(4))
            assertThat(successItem.hotelsValue[1]).isEqualTo(HotelListUIItem("Hotel 4"))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `WHEN repository returns with error THEN the viewmodel update the uiStateFlow to loading and after UiState_Error`() = runBlocking {
        sut = HotelsListViewModel(HotelsListRepositoryDouble(ERROR))
        sut.uiState.test {
            sut.getHotelsList()
            assertThat(awaitItem()).isInstanceOf(HotelListUIState.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(HotelListUIState.Error::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `WHEN getHotelWithSku is called THEN the viewmodel returns the expected HotelDetailUIModel`() {
        // awaiting finish ui of the list first...
    }

    private class HotelsListRepositoryDouble(
        private val expectedResponseStatus: ExpectedResponseStatus
    ): IHotelsListRepository {
        enum class ExpectedResponseStatus {
            SUCCESS, ERROR
        }

        override suspend fun getHotelList(): HotelsListDomainState = when (expectedResponseStatus) {
            SUCCESS -> HotelsListDomainState.Success(HOTEL_DOMAIN_LIST)
            ERROR -> HotelsListDomainState.Error
        }

        override fun getHotelWithSku(hotelId: String): Hotel? {
            TODO("Not yet implemented")
        }
    }
}