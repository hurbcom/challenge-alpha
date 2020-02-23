package app.recrutamento.android.challengealpha.repository.api.remotedata

import app.recrutamento.android.challengealpha.model.hotel.Hotel

interface HotelDataSource {

    suspend fun listAllHotels(
        local: String,
        pageNumber: String,
        success: (MutableList<Hotel>) -> Unit,
        failure: () -> Unit
    )

}