package com.isranascimento.lastviewed.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isranascimento.coremodels.hotel.Hotel
import com.isranascimento.lastviewed.repository.ILastViewedRepository
import com.isranascimento.lastviewed.ui.models.LastViewedUIState
import com.isranascimento.theme.hotel.HotelCardItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LastViewedViewModel(
    private val repository: ILastViewedRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<LastViewedUIState>(LastViewedUIState.Empty)
    val uiState = _uiState.asStateFlow()

    init {
        getLastHotelsViewed()
    }

    fun getLastHotelsViewed() {
        viewModelScope.launch {
            repository
                .getLastViewed()
                .onEach { hotelList ->
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
                .launchIn(this)
        }
    }

    fun getHotelWithId(id: String): Hotel {
        return repository.getHotelWithId(id)!!
    }
}