package com.isranascimento.hotels.repository

import com.isranascimento.database.HotelsDAO
import com.isranascimento.databasedtos.hotels.HotelDatabaseEntity
import com.isranascimento.hotels.TimerHelper
import com.isranascimento.hotels.util.ReturnedValues.HOTEL_DOMAIN_LIST
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HotelsDetailRepositoryTest {
    private lateinit var sut: HotelsDetailRepository
    private lateinit var dao: HotelsDAO

    @Before
    fun setup() {
        dao = mockk()
        mockkObject(TimerHelper)
        every { TimerHelper.getNow() } returns 1L
        coEvery { dao.insertHotelWithAmenities(any(), any()) } just Runs
        sut = HotelsDetailRepository(dao)
    }

    @Test
    fun `WHEN save lastviewed is called THEN it calls the dao with correct params`() = runBlocking {
        sut.insertIntoLastViewed(HOTEL_DOMAIN_LIST[0])
        coVerify {
            dao.insertHotelWithAmenities(HotelDatabaseEntity(
                id = "1",
                city = "City 1",
                state = "State 1",
                mainImage = "Image 1",
                name = "Hotel 1",
                insertedTime = 1L
            ), HOTEL_DOMAIN_LIST[0].amenities)
        }
    }
}