package app.recrutamento.android.challengealpha.repository.api

import app.recrutamento.android.challengealpha.model.hotel.HotelResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface HotelApiService {

    @GET("api")
    fun listHotel(@Query("q") location: String?, @Query("page") page: String?): Deferred<Response<HotelResponse>>

}