package com.isranascimento.hotelslist.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isranascimento.hotelslist.models.HotelsListDomainState
import com.isranascimento.hotelslist.repository.IHotelsListRepository
import com.isranascimento.hotelslist.ui.viewmodels.models.HotelListUI
import com.isranascimento.hotelslist.ui.viewmodels.models.HotelListUIState
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
}