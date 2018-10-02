package com.belfortdev.hurbchallenge.list.data

import com.belfortdev.hurbchallenge.core.data.remote.SearchService
import com.belfortdev.hurbchallenge.list.test.DummyHotel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Test

class ListRemoteDataTest {

    private val searchService = mock<SearchService>()

    @Test
    fun getHotels() {
        whenever(
                searchService.getAllHotelsFromRio()).thenReturn(
                Single.just(DummyHotel.getFullResponse("321321"))
        )

        ListRemoteData(searchService).getHotels().test().run {
            assertNoErrors()
            assertValueCount(1)

            val hotel = values()[0]!![0]

            Assert.assertEquals(values()[0]!!.size, 1)
            Assert.assertEquals(hotel!!.id, "321321")
            Assert.assertEquals(hotel!!.isHotel, true)
        }
    }

}