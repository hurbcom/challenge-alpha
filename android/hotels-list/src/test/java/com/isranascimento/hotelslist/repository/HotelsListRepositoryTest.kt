package com.isranascimento.hotelslist.repository

import com.google.common.truth.Truth.assertThat
import com.isranascimento.datatransferobjects.hotels.HotelsResponse
import com.isranascimento.hotelslist.models.Address
import com.isranascimento.hotelslist.models.Hotel
import com.isranascimento.hotelslist.models.HotelsListDomainState
import com.isranascimento.hotelslist.repository.HotelsListRepositoryTest.HotelsDataSourceDouble.ExpectedResponseStatus.ERROR
import com.isranascimento.hotelslist.repository.HotelsListRepositoryTest.HotelsDataSourceDouble.ExpectedResponseStatus.SUCCESS
import com.isranascimento.network.response.NetworkResponse
import com.isranascimento.network.service.IHotelsRemoteDataSource
import com.isranascimento.testutils.ExpectedHotelsResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class HotelsListRepositoryTest {
    companion object {
        private const val EXPECTED_HOTEL_LIST_SIZE = 1
    }

    private lateinit var sut: HotelsListRepository

    @Test
    fun `WHEN the remote datasource returns with success THEN the repository maps it to the success domain class`() = runBlocking {
        sut = HotelsListRepository(HotelsDataSourceDouble(SUCCESS))
        val hotelsState = sut.getHotelList()

        assertThat(hotelsState).isInstanceOf(HotelsListDomainState.Success::class.java)

        val hotelList = (hotelsState as HotelsListDomainState.Success).hotelList
        assertThat(hotelList.size).isEqualTo(EXPECTED_HOTEL_LIST_SIZE)
        assertThat(hotelList.first()).isEqualTo(expectedFirstHotel())
    }

    private fun expectedFirstHotel(): Hotel = Hotel(
        id = "AT7987",
        sku = "NHU-7987-0-0-0-0",
        name = "Pousada Aardvark Inn",
        gallery = listOf(
            "https://static.hotelurbano.com/reservas/prod0/7/7987/5d28d5cf6fa3c_" +
                    "pousada-aardvark-inn.jpg",
            "https://static.hotelurbano.com/reservas/prod0/7/7987/5d28d5d8bfd46_" +
                    "pousada-aardvark-inn.jpg"
        ),
        amenities = listOf("Bar", "Wifi"),
        price = 287.88,
        address = Address(
            "Rio Grande do Sul",
            "Gramado"
        ),
        starCount = 3
    )

    @Test
    fun `WHEN the remote datasource returns with error THEN the repository maps it to the error domain class`() = runBlocking {
        sut = HotelsListRepository(HotelsDataSourceDouble(ERROR))
        val hotelsState = sut.getHotelList()
        assertThat(hotelsState).isInstanceOf(HotelsListDomainState.Error::class.java)
    }

    @Test
    fun `WHEN getHotelList is called with correct SKU THEN the hotel domain is returned`() = runBlocking {
        sut = HotelsListRepository(HotelsDataSourceDouble(SUCCESS))
        sut.getHotelList()
        val hotel = sut.getHotelWithSku("AT7987")

        assertThat(hotel).isEqualTo(expectedFirstHotel())
    }

    private class HotelsDataSourceDouble(
        private val expectedResponseStatus: ExpectedResponseStatus
    ): IHotelsRemoteDataSource {
        enum class ExpectedResponseStatus {
            SUCCESS, ERROR
        }

        override suspend fun getHotelsList(): NetworkResponse<HotelsResponse> =
            when (expectedResponseStatus) {
                SUCCESS -> NetworkResponse.Success(Response.success(ExpectedHotelsResponse.expectedResponse()))
                ERROR -> NetworkResponse.GenericError()
            }
    }
}