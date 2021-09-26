package com.filipeoliveira.hurbchallenge.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipeoliveira.hurbchallenge.R
import com.filipeoliveira.hurbchallenge.data.HotelRepository
import com.filipeoliveira.hurbchallenge.ui.UIState
import com.filipeoliveira.hurbchallenge.ui.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class HotelListViewModel(
    val repository: HotelRepository
) : ViewModel() {

    private val _hotelList = MutableLiveData<UIState<List<HotelUI>>>()
    val hotelList: LiveData<UIState<List<HotelUI>>>
        get() = _hotelList


    fun loadHotelList() {
        _hotelList.value = UIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getHotelList()
                Thread.sleep(2000)

                when((1..10).random()){
                    1 -> throw UnknownHostException()
                    2 -> throw Exception()
                    else -> {
                        _hotelList.postValue(UIState.Success(response))
                    }
                }

            } catch (e: UnknownHostException){
                Log.e(TAG, e.stackTraceToString())
                _hotelList.postValue(UIState.Error(R.string.hint_unavailable_network_error))
            } catch (e: Exception){
                _hotelList.postValue(UIState.Error(R.string.hint_generic_network_error))
            }
        }
    }

    companion object {
        const val TAG = "HotelListViewModel"
    }
}
