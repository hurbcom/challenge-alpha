package com.filipeoliveira.hurbchallenge.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipeoliveira.hurbchallenge.data.HotelRepository
import com.filipeoliveira.hurbchallenge.ui.UIState
import com.filipeoliveira.hurbchallenge.ui.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotelListViewModel(
    val repository: HotelRepository
) : ViewModel() {

    private val _hotelList = MutableLiveData<UIState<List<HotelUI>>>()
    val hotelList: LiveData<UIState<List<HotelUI>>>
        get() = _hotelList


    fun loadHotelList() {
        _hotelList.value = UIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getHotelList()

            _hotelList.postValue(UIState.Success(response))
        }
    }
}
