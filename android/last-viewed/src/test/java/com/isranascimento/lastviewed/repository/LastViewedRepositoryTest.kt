package com.isranascimento.lastviewed.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.isranascimento.database.IRetrieveHotelList
import com.isranascimento.databasedtos.hotels.HotelDatabaseEntity
import com.isranascimento.databasedtos.hotels.HotelsAmenityDatabaseEntity
import com.isranascimento.databasedtos.hotels.HotelsGalleryItemDatabaseEntity
import com.isranascimento.databasedtos.hotels.HotelsWithAmenitiesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LastViewedRepositoryTest {
    private lateinit var sut: LastViewedRepository

    private lateinit var retrieveHotelListDouble: IRetrieveHotelList

    @Before
    fun setup() {
        retrieveHotelListDouble = RetrieveHotelListDouble()
        sut = LastViewedRepository(
            retrieveHotelListDouble
        )
    }


    @Test
    fun `WHEN getLastViewed is called THEN it returns the flow with domain data`(): Unit = runBlocking {
        val value = sut.getLastViewed()

        value.test {
            val hotelList = awaitItem()
            assertThat(hotelList.size).isEqualTo(2)
            assertThat(hotelList[0].hotel.id).isEqualTo("1")
            assertThat(hotelList[1].hotel.id).isEqualTo("2")
            cancelAndConsumeRemainingEvents()
        }
        assertThat(value)
    }

    class RetrieveHotelListDouble: IRetrieveHotelList {
        override fun getHotelsWithAmenities(): Flow<List<HotelsWithAmenitiesEntity>> {
            return flowOf(
                listOf(
                    createHotelEntity(1),
                    createHotelEntity(2)
                )
            )
        }

        private fun createHotelEntity(number: Int): HotelsWithAmenitiesEntity {
            return HotelsWithAmenitiesEntity(
                createHotelDatabaseEntity(number),
                listOf(createHotelAmenityDatabaseEntity(number)),
                listOf(createHotelGalleryDatabaseEntity(number))
            )

        }

        private fun createHotelAmenityDatabaseEntity(number: Int): HotelsAmenityDatabaseEntity {
            return HotelsAmenityDatabaseEntity(
                id = number.toLong(),
                value = "Amenity $number",
                hotelId = "HotelId $number"
            )
        }

        private fun createHotelGalleryDatabaseEntity(number: Int): HotelsGalleryItemDatabaseEntity {
            return HotelsGalleryItemDatabaseEntity(
                id = number.toLong(),
                value = "Amenity $number",
                hotelId = "HotelId $number"
            )
        }

        private fun createHotelDatabaseEntity(number: Int): HotelDatabaseEntity {
            return HotelDatabaseEntity(
                id = number.toString(),
                insertedTime = number.toLong(),
                city = "City $number",
                state = "State $number",
                mainImage = "MainImage $number",
                name = "Name $number"
            )
        }
    }
}