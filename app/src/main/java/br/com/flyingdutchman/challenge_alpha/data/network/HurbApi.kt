package br.com.flyingdutchman.challenge_alpha.data.network

import br.com.flyingdutchman.challenge_alpha.data.model.ApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HurbApi{

    @GET("api?")
    fun search(
        @Query("q") q: String = "buzios",
        @Query("page") page: Int = 1,
        @Query(encoded = true, value = "filters") filters: String
    ): Single<ApiResponse>
}