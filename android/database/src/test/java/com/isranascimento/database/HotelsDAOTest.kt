package com.isranascimento.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.isranascimento.androidtestutils.TestContextProvider.context
import com.isranascimento.database.utils.createHotelEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HotelsDAOTest {
    private lateinit var database: HotelsRoomDatabase
    private lateinit var sut: HotelsDAO

    @Before
    fun setup() {
        database = Room
            .inMemoryDatabaseBuilder(context(), HotelsRoomDatabase::class.java)
            .build()
        sut = database.hotelsDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `WHEN returning a list of hotels THEN it returns the list in correct order`() = runBlocking {
        sut._insertHotel(createHotelEntity(1))
        sut._insertHotel(createHotelEntity(2))

        sut.getHotelsWithAmenities().test {
            val hotels = awaitItem()
            assertThat(hotels[0].hotel.id).isEqualTo("2")
            assertThat(hotels[1].hotel.id).isEqualTo("1")
        }
    }

    @Test
    fun `WHEN adding a hotel with amenities THEN the item is added correctly`() = runBlocking {
        sut.insertHotel(createHotelEntity(1), listOf("Amenity 1"), listOf("Gallery 1"))

        sut.getHotelsWithAmenities().test {
            val hotels = awaitItem()
            assertThat(hotels[0].hotel.id).isEqualTo("1")
            assertThat(hotels[0].amenities.size).isEqualTo(1)
            assertThat(hotels[0].amenities[0].value).isEqualTo("Amenity 1")
            assertThat(hotels[0].gallery.size).isEqualTo(1)
            assertThat(hotels[0].gallery[0].value).isEqualTo("Gallery 1")
        }
    }

    @Test
    fun `WHEN insert a new Hotel with the same sku THEN the hotel is replaced`() = runBlocking {
        sut._insertHotel(createHotelEntity(1, insertedTime = 1))
        sut._insertHotel(createHotelEntity(1, insertedTime = 2))
        sut.getHotelsWithAmenities().test {
            val hotels = awaitItem()
            assertThat(hotels.size).isEqualTo(1)
            assertThat(hotels[0].hotel.id).isEqualTo("1")
            assertThat(hotels[0].hotel.insertedTime).isEqualTo(2)
        }
    }
}