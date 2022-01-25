package com.br.natanbrito.challenge.alpha.hotels_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.br.natanbrito.challenge.data.datasource.HotelDataSourceImpl
import com.br.natanbrito.challenge.data.model.Hotel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelsViewModel @Inject constructor(
    private val repository: HotelDataSourceImpl
) : ViewModel() {

    private val _hotels = MutableLiveData<Hotel?>()
    val hotels get() = _hotels

    init {
        getHotels()
    }

    private fun getHotels() = viewModelScope.launch {
        val hotelsResult = repository.fetchHotels()
        _hotels.value = hotelsResult
    }

}