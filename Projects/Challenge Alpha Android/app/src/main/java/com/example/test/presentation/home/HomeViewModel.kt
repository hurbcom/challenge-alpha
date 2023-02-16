package com.example.test.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.usecases.home.GetPeopleUseCase
import com.example.test.domain.usecases.home.ListGetParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getPeopleUseCase: GetPeopleUseCase) :
    ViewModel() {
    fun getPeople() {
        viewModelScope.launch {
            getPeopleUseCase.performAction(ListGetParams(1))
                .collect {
                    Log.e("RESPONSE", "" + it.toString())
                }
        }
    }
}