package app.recrutamento.android.challengealpha.repository.remotedata

import app.recrutamento.android.challengealpha.model.hotel.Hotel
import app.recrutamento.android.challengealpha.repository.api.HotelApiService
import app.recrutamento.android.challengealpha.repository.api.remotedata.HotelDataSource
import kotlinx.coroutines.*

open class HotelRemoteDataSource(val hotelApiService: HotelApiService) :
    HotelDataSource {
    private var myJob: Job? = null

    override suspend fun listAllHotels(
        local: String,
        pageNumber: String,
        success: (MutableList<Hotel>) -> Unit,
        failure: () -> Unit
    ) {

        myJob = CoroutineScope(Dispatchers.Main).launch {
            val result = hotelApiService.listHotel(local, pageNumber).await()

            withContext(Dispatchers.Main) {
                val itens = result.body()?.results
                if (result.isSuccessful) {
                    if (itens!!.isEmpty()) {
                        failure()
                    }
                    success(itens)
                } else {
                    failure()
                }
            }
        }
    }
}