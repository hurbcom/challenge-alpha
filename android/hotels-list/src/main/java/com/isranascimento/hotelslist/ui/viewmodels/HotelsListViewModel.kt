package com.isranascimento.hotelslist.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isranascimento.hotelslist.models.HotelsListDomainState
import com.isranascimento.hotelslist.repository.IHotelsListRepository
import com.isranascimento.hotelslist.ui.viewmodels.models.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HotelsListViewModel(
    private val repository: IHotelsListRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getHotelsList() {
        _uiState.value = UIState.Loading
        viewModelScope.launch {
            when(repository.getHotelList()) {
                is HotelsListDomainState.Success -> {
                    _
                }
                is HotelsListDomainState.Error -> {

                }
            }
        }
    }
}