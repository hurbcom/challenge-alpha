package br.com.loubake.challenge_hu.hotels

import android.content.Context
import br.com.loubake.challenge_hu.R
import br.com.loubake.challenge_hu.data.ApiService
import br.com.loubake.challenge_hu.data.HotelResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotelsPresenter(var context: Context, var mHotelsView: HotelsContract.View) : HotelsContract.Presenter {

    override fun loadHotels() {
        val query = "Rio de Janeiro"
        val service = ApiService(context)
        service.getHotelsApi().listHotels(query).enqueue(object : Callback<HotelResults> {
            override fun onResponse(call: Call<HotelResults>?, response: Response<HotelResults>?) {
                val results = (response?.body() as HotelResults)?.hotels

                if (results == null || results.isEmpty()) {
                    mHotelsView.showErrorLayout()
                    mHotelsView.hideLoading()
                    return
                }

                var hotelsList = handleHotelsResponse(results)
                mHotelsView.setHotelsList(hotelsList)
                mHotelsView.hideLoading()
            }

            override fun onFailure(call: Call<HotelResults>?, t: Throwable?) {
                mHotelsView.showErrorLayout()
                mHotelsView.hideLoading()
            }
        })
    }

    override fun handleHotelsResponse(results: List<HotelResults.Hotel>) : List<Any> {
        var hotels = ArrayList<HotelResults.Hotel>(results)

        hotels.sortByDescending {
            it.stars
        }
        var groupedList = ArrayList<Any>(hotels)

        val starsCount : Int? = (groupedList[0] as HotelResults.Hotel)?.stars
        if (starsCount != null) {
            if (starsCount == 0) {
                groupedList.add(0, context.getString(R.string.group_package))
            } else {
                groupedList.add(0, context.getString(R.string.group_stars, starsCount))
            }
            for (i in 1..groupedList.size - 1) {
                if (groupedList[i] !is String) {
                    if (i + 1 < groupedList.size) {
                        val currentItem = groupedList[i] as HotelResults.Hotel
                        val nextItem = groupedList[i+1] as HotelResults.Hotel
                        if (nextItem.isPackage && currentItem.isHotel) {
                            groupedList.add(i+1, context.getString(R.string.group_package))
                            break
                        }
                        if (nextItem.stars != currentItem.stars) {
                            groupedList.add(i+1, context.getString(R.string.group_stars, nextItem.stars))
                        }
                    }
                }
            }
        }

        return groupedList as List<HotelResults.Hotel>
    }
}