package com.example.hurb_challenge.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.hurb_challenge.app.domain.model.CardItem
import com.example.hurb_challenge.app.domain.usecase.HomeUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class HomeBaseViewModel<T : Any>(
    private val useCase: HomeUseCase<T>,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _cardItemStateFlow = MutableStateFlow<PagingData<CardItem<T>>>(PagingData.empty())
    val cardItemStateFlow: StateFlow<PagingData<CardItem<T>>> = _cardItemStateFlow.asStateFlow()

    fun loadCardItems() {
        viewModelScope.launch(dispatcher) {
            useCase.call()
                .cachedIn(viewModelScope)
                .collect { cardItems ->
                    _cardItemStateFlow.emit(cardItems)
                }
        }
    }
}
