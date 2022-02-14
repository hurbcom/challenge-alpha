package com.isranascimento.hotels.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isranascimento.hotels.models.HotelsListDomainState
import com.isranascimento.hotels.repository.IHotelsListRepository
import com.isranascimento.hotels.ui.models.HotelDetailUI
import com.isranascimento.hotels.ui.models.HotelListUI
import com.isranascimento.hotels.ui.models.HotelListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HotelsListViewModel(
    private val repository: IHotelsListRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<HotelListUIState>(HotelListUIState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getHotelsList() {
        _uiState.value = HotelListUIState.Loading
        viewModelScope.launch {
            when(val value = repository.getHotelList()) {
                is HotelsListDomainState.Success -> {
                    _uiState.value = HotelListUIState.Success(HotelListUI.fromHotelListDomainClass(value.hotelList))
                }
                is HotelsListDomainState.Error -> {
                    _uiState.value = HotelListUIState.Error
                }
            }
        }
    }

    fun getHotelDetailUIModel(sku: String): HotelDetailUI {
        val hotel = repository.getHotelWithSku(sku)
        return HotelDetailUI.fromDomainModel(hotel!!)
    }
}