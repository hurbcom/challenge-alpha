package app.recrutamento.android.challengealpha.repository.api.remotedata

import app.recrutamento.android.challengealpha.model.hotel.Hotel
import app.recrutamento.android.challengealpha.repository.HotelRepository
import app.recrutamento.android.challengealpha.ui.hotellist.HotelListViewModel
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.Test

import org.junit.Assert.*

class HotelRemoteDataSourceTest {

    private val hotelDataSource: HotelRemoteDataSource = mock()

    @Test
    fun listAllHotels_empty() {
        val hotels = ArrayList<Hotel>()
        runBlocking {
            hotelDataSource.listAllHotels(
                "zzz",
                "1",
                { it.forEach { hotels.add(it) } },
                {})
        }

        assertThat(hotels.isEmpty(), CoreMatchers.`is`(true))

    }

    @Test
    fun listAllHotels_count20() {
        val hotels = ArrayList<Hotel>()
        runBlocking {
            hotelDataSource.listAllHotels(
                "Búzios",
                "1",
                { it.forEach { hotels.add(it) } },
                {})
        }

        assertThat(hotels.size, CoreMatchers.`is`(20))

    }
}