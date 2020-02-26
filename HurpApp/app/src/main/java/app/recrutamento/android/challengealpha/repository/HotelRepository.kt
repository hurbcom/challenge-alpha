package app.recrutamento.android.challengealpha.repository

import app.recrutamento.android.challengealpha.model.hotel.Hotel
import app.recrutamento.android.challengealpha.repository.api.remotedata.HotelRemoteDataSource

class HotelRepository(private val hotelRemoteDataSource: HotelRemoteDataSource) {

    suspend fun listAllHotels(
        local: String,
        pageNumber: String, success: (MutableList<Hotel>) -> Unit, failure: () -> Unit
    ) = hotelRemoteDataSource.listAllHotels(local, pageNumber, success, failure)

}