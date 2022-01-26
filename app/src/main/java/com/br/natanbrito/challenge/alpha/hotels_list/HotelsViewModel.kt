package com.br.natanbrito.challenge.alpha.hotels_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.br.natanbrito.challenge.alpha.R
import com.br.natanbrito.challenge.data.datasource.HotelDataSourceImpl
import com.br.natanbrito.challenge.data.model.Hotel
import com.br.natanbrito.challenge.data.model.HotelNetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelsViewModel @Inject constructor(
    private val repository: HotelDataSourceImpl
) : ViewModel() {

    private val _hotels = MutableLiveData<Hotel>()
    val hotels get() = _hotels
    private val _errorMessage = MutableLiveData<Any>()
    val errorMessage get() = _errorMessage
    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected get() = _isConnected

    fun prepareDataRequest(hasInternetConnection: Boolean) {
        _isConnected.value = hasInternetConnection

        if (hasInternetConnection) {
            getHotels()
        } else {
            _errorMessage.value = R.string.you_are_offline
        }
    }

    private fun getHotels() = viewModelScope.launch {
        val hotelsResult = repository.fetchHotels()
        when(hotelsResult) {
            is HotelNetworkResult.Success -> {
                _hotels.value = hotelsResult.hotel
            }
            is HotelNetworkResult.Error -> {
                _errorMessage.value = hotelsResult.message
            }
        }
    }

}