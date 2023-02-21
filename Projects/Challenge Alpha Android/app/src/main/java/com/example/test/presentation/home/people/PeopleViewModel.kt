package com.example.test.presentation.home.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseResult
import com.example.test.domain.models.Person
import com.example.test.domain.usecases.home.GetPeopleUseCase
import com.example.test.domain.usecases.ListGetParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val getPeopleUseCase: GetPeopleUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow<BaseResult<List<Person>>>(BaseResult.Loading)
    val state: StateFlow<BaseResult<List<Person>>> = _state

    fun getPeople(page: Int = 1) {
        viewModelScope.launch {
            _state.value = BaseResult.Loading
            getPeopleUseCase.performAction(ListGetParams(page)).collect { _state.value = it }
        }
    }
}