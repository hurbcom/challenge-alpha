package viniciusapp.com.br.hurbtest.repository

import io.reactivex.Observable
import retrofit2.http.*
import viniciusapp.com.br.hurbtest.models.HotelModel
import viniciusapp.com.br.hurbtest.models.SuggestionsHotelModel

interface Endpoints {
    @GET("search/api")
    fun getHotels(
        @Query("q") city : String,
        @Query("page") page: Int) : Observable<HotelModel>

    @GET("search/api/suggestion")
    fun getSuggestions(
        @Query("q") city : String) : Observable<SuggestionsHotelModel>

}