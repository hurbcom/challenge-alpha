package com.isranascimento.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.runner.AndroidJUnit4
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.isranascimento.androidtestutils.TestContextProvider.context
import com.isranascimento.database.HotelsDao
import com.isranascimento.database.utils.createHotelEntity
import com.isranascimento.hotelslist.database.HotelsRoomDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HotelsDAOTest {
    private lateinit var database: HotelsRoomDatabase
    private lateinit var sut: HotelsDao

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
        sut.insertHotel(createHotelEntity(1))
        sut.insertHotel(createHotelEntity(2))

        sut.getHotelList().test {
            val hotels = awaitItem()
            assertThat(hotels[0].id).isEqualTo("2")
            assertThat(hotels[1].id).isEqualTo("1")
        }
    }

    @Test
    fun `WHEN insert a new Hotel with the same sku THEN the hotel is replaced`() = runBlocking {
        sut.insertHotel(createHotelEntity(1, insertedTime = 1))
        sut.insertHotel(createHotelEntity(1, insertedTime = 2))
        sut.getHotelList().test {
            val hotels = awaitItem()
            assertThat(hotels.size).isEqualTo(1)
            assertThat(hotels[0].id).isEqualTo("1")
            assertThat(hotels[0].insertedTime).isEqualTo(2)
        }
    }
}