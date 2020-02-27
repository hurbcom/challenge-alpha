package app.recrutamento.android.challengealpha.repository.api.remotedata

import androidx.paging.PageKeyedDataSource
import app.recrutamento.android.challengealpha.model.hotel.Hotel
import app.recrutamento.android.challengealpha.repository.api.HotelApiService
import kotlinx.coroutines.*
import timber.log.Timber
import java.lang.Exception

open class HotelRemoteDataSource(
    private val hotelApiService: HotelApiService
) : HotelDataSource {

    private var myJob: Job? = null

    override suspend fun listAllHotels(
        local: String,
        pageNumber: String,
        success: (MutableList<Hotel>) -> Unit,
        failure: () -> Unit
    ) {
        myJob = CoroutineScope(Dispatchers.Main).launch {
            try {
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
            } catch (ex: Exception) {
                Timber.e(ex)
                failure()
            }
        }
    }
}