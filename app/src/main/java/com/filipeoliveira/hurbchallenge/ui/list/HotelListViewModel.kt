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

    private lateinit var filters: FilterUI
    private val enabledFilters = mutableListOf<String>()

    init {
        loadHotelList()
    }

    fun loadHotelList(query: String = "") {
        _hotelList.value = UIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getHotelList(query, enabledFilters)
                Thread.sleep(2000)

                when ((1..20).random()) {
                    1 -> throw UnknownHostException()
                    2 -> throw Exception()
                    else -> {
                        filters = response.filters
                        _hotelList.postValue(UIState.Success(response.hotelList))
                    }
                }

            } catch (e: UnknownHostException) {
                _hotelList.postValue(UIState.Error(R.string.hint_unavailable_network_error))
            } catch (e: Exception) {
                Log.e(TAG, e.stackTraceToString())
                _hotelList.postValue(UIState.Error(R.string.hint_generic_network_error))
            }
        }
    }

    fun getAvailableFilters(): FilterUI? {
        return if (this::filters.isInitialized){
            this.filters
        } else {
            null
        }
    }


    fun isOnEnabledFilterList(filter: String) : Boolean {
        return enabledFilters.contains(filter)
    }

    fun addFilter(filter: String){
        enabledFilters.add(filter)
    }

    fun removeFilter(filter: String){
        enabledFilters.remove(filter)
    }

    companion object {
        const val TAG = "HotelListViewModel"
    }
}
