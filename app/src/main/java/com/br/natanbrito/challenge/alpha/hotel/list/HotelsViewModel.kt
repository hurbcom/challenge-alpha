package com.br.natanbrito.challenge.alpha.hotel.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.natanbrito.challenge.alpha.R
import com.br.natanbrito.challenge.data.model.Hotel
import com.br.natanbrito.challenge.data.model.HotelNetworkResult
import com.br.natanbrito.challenge.domain.usecases.GetHotelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HotelsViewModel @Inject constructor(
    private val useCase: GetHotelsUseCase
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
        when (val hotelsResult = useCase.invoke()) {
            is HotelNetworkResult.Success -> {
                _hotels.value = hotelsResult.hotel
            }
            is HotelNetworkResult.Error -> {
                _errorMessage.value = hotelsResult.message
            }
        }
    }
}
