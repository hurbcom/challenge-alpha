package br.com.loubake.challenge_hu.data

import android.content.Context
import br.com.loubake.challenge_hu.R
import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class ApiService(context: Context) {
    var retrofit = Retrofit.Builder()
        .baseUrl(context.getString(R.string.base_url))
        .addConverterFactory(GsonConverterFactory.create(getHotelResultsTypeAdapter()))
        .build()

    fun getHotelsApi() : HotelsService {
        return retrofit.create(HotelsService::class.java)
    }

    fun getHotelResultsTypeAdapter() : Gson {
        return GsonBuilder().registerTypeAdapter(HotelResults::class.java, HotelResultsDeserializer()).create()
    }

    class HotelResultsDeserializer : JsonDeserializer<HotelResults> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HotelResults {
            var jsonObject: JsonObject = json!!.asJsonObject
            var response = Gson().fromJson(json, HotelResults::class.java)
            var resultsNode: JsonElement = jsonObject.get("results")

            for (i in 0..resultsNode.asJsonArray.size() - 1) {
                if ((resultsNode.asJsonArray[i] as JsonObject).has("isHotel")) {
                    response.hotels[i].price = Gson().fromJson(resultsNode.asJsonArray[i].asJsonObject.get("price"), HotelResults.Hotel.HotelPrice::class.java)
                } else if ((resultsNode.asJsonArray[i] as JsonObject).has("isPackage")) {
                    response.hotels[i].price = Gson().fromJson(resultsNode.asJsonArray[i].asJsonObject.get("price"), HotelResults.Hotel.PackagePrice::class.java)
                }
            }
            return response
        }
    }
}