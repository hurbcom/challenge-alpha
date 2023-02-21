package com.example.test.presentation.home.starships

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseResult
import com.example.test.domain.models.Starship
import com.example.test.domain.usecases.ListGetParams
import com.example.test.domain.usecases.home.GetStarshipsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarshipsViewModel @Inject constructor(
    private val getStarshipsUseCase: GetStarshipsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<BaseResult<List<Starship>>>(BaseResult.Loading)
    val state: StateFlow<BaseResult<List<Starship>>> = _state

    fun getStarships(page: Int) {
        viewModelScope.launch {
            _state.value = BaseResult.Loading
            getStarshipsUseCase.performAction(ListGetParams(page)).collect { _state.value = it }
        }
    }
}