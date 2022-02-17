package com.isranascimento.lastviewed.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isranascimento.lastviewed.repository.ILastViewedRepository
import com.isranascimento.lastviewed.ui.models.LastViewedUIState
import com.isranascimento.theme.hotel.HotelCardItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LastViewedViewModel(
    private val repository: ILastViewedRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<LastViewedUIState>(LastViewedUIState.Empty)
    val uiState = _uiState.asStateFlow()

    fun getLastHotelsViewed() {
        viewModelScope.launch {
            repository.getLastViewed().collect { hotelList ->
                if(hotelList.isEmpty()) {
                    _uiState.value = LastViewedUIState.Empty
                } else {
                    _uiState.value = LastViewedUIState.WithItem(
                        hotelList.map { hotel ->
                            HotelCardItem.fromDomainModel(hotel)
                        }
                    )
                }
            }
        }
    }
}