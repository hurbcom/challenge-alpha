package app.recrutamento.android.challengealpha.ui.hotellist

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import app.recrutamento.android.challengealpha.di.networkModule
import app.recrutamento.android.challengealpha.di.repositoryModule
import app.recrutamento.android.challengealpha.di.viewModelModule
import app.recrutamento.android.challengealpha.model.hotel.Hotel
import app.recrutamento.android.challengealpha.repository.HotelRepository
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.koin.android.ext.koin.with
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.test.KoinTest
import org.mockito.Mockito.mock

class HotelListViewModelTest : KoinTest{

    private val repository = HotelRepository()
    private val hotelListViewModel = HotelListViewModel(repository, ApplicationProvider.getApplicationContext())
    private val hotels = listOf(
        Hotel(name = "Hotel 1",stars = 3),
        Hotel(name = "Hotel 2",stars = 1),
        Hotel(name = "Hotel 3",stars = 4),
        Hotel(name = "Hotel 4",stars = 2),
        Hotel(name = "Hotel 5",stars = 3),
        Hotel(name = "Hotel 6",stars = 5),
        Hotel(name = "Hotel 7",stars = 5),
        Hotel(name = "Hotel 8",stars = 3)
    )

    @Test
    fun searchAndSort_threeStars_loading(){
        startKoin(listOf(viewModelModule, repositoryModule, networkModule)) with (mock(Context::class.java))
        val hotelsFiltered = hotels.filter { it.stars == 3 }.toMutableList()
        val hotelsSorted = hotelListViewModel.searchAndSort(hotels.toMutableList(),"3")
        assertThat(hotelsSorted,`is` (hotelsFiltered))
    }
}