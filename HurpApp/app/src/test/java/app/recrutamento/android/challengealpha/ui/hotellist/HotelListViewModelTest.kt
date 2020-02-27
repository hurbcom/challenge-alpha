package app.recrutamento.android.challengealpha.ui.hotellist

import app.recrutamento.android.challengealpha.HotelMock
import app.recrutamento.android.challengealpha.repository.HotelRepository
import app.recrutamento.android.challengealpha.repository.api.remotedata.HotelRemoteDataSource
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.koin.test.KoinTest

class HotelListViewModelTest : KoinTest{

    private val hotelDataSource:HotelRemoteDataSource = mock()
    private val repository:HotelRepository = HotelRepository(hotelDataSource)
    private val hotelListViewModel = HotelListViewModel(repository, mock())

    @Test
    fun searchAndSort_threeStars_loading(){
        val hotelsSorted = hotelListViewModel.searchAndSort(HotelMock.hotelList,"3")
        assertThat(hotelsSorted,`is` (HotelMock.HotelListFilteredThreeStars))
    }

}