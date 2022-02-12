package com.isranascimento.hotelslist.ui.viewmodels

import com.isranascimento.hotelslist.models.Hotel
import com.isranascimento.hotelslist.models.HotelsListDomainState
import com.isranascimento.hotelslist.repository.IHotelsListRepository
import com.isranascimento.hotelslist.ui.viewmodels.HotelsListViewModelTest.HotelsListRepositoryDouble.ExpectedResponseStatus.SUCCESS
import org.junit.Test

class HotelsListViewModelTest {
    private lateinit var sut: HotelsListViewModel

    @Test
    fun `WHEN repository returns with success THEN the viewmodel update the uiStateFlow to loading and after UiState_Success expected value`() {
        sut = HotelsListViewModel(HotelsListRepositoryDouble(SUCCESS))
        sut.getHotelsList()
    }

    @Test
    fun `WHEN repository returns with error THEN the viewmodel update the uiStateFlow to loading and after UiState_Error`() {
        // awaiting...
    }

    @Test
    fun `WHEN getHotelWithSku is called THEN the viewmodel returns the expected HotelDetailUIModel`() {
        // awaiting...
    }

    private class HotelsListRepositoryDouble(
        private val expectedResponseStatus: ExpectedResponseStatus
    ): IHotelsListRepository {
        enum class ExpectedResponseStatus {
            SUCCESS, ERROR
        }

        override suspend fun getHotelList(): HotelsListDomainState = when (expectedResponseStatus) {
            SUCCESS -> HotelsListDomainState.Success(listOf())
            ExpectedResponseStatus.ERROR -> HotelsListDomainState.Error
        }

        override fun getHotelWithSku(hotelId: String): Hotel? {
            TODO("Not yet implemented")
        }
    }
}