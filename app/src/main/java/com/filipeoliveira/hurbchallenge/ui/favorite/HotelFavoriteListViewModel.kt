package com.filipeoliveira.hurbchallenge.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipeoliveira.hurbchallenge.R
import com.filipeoliveira.hurbchallenge.data.HotelRepository
import com.filipeoliveira.hurbchallenge.ui.UIState
import com.filipeoliveira.hurbchallenge.ui.model.HotelUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotelFavoriteListViewModel(
    val repository: HotelRepository
) : ViewModel() {

    private val _hotelList = MutableLiveData<UIState<List<HotelUI>>>()
    val hotelList: LiveData<UIState<List<HotelUI>>>
        get() = _hotelList

    fun loadFavoriteHotels() {
        _hotelList.value = UIState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getFavoriteHotels()
                _hotelList.postValue(UIState.Success(response))
            } catch (e: Exception){
                _hotelList.postValue(UIState.Error(R.string.hint_generic_network_error))
            }
        }
    }
}