package com.example.test.presentation.home.planets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseResult
import com.example.test.domain.models.Planet
import com.example.test.domain.usecases.ListGetParams
import com.example.test.domain.usecases.home.GetPlanetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<BaseResult<List<Planet>>>(BaseResult.Loading)
    val state: StateFlow<BaseResult<List<Planet>>> = _state

    fun getPlanets(page: Int) {
        viewModelScope.launch {
            _state.value = BaseResult.Loading
            getPlanetsUseCase.performAction(ListGetParams(page)).collect { _state.value = it }
        }
    }
}