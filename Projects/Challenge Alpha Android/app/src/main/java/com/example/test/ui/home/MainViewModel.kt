package com.example.test.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.usecases.UseCase
import com.example.test.domain.usecases.params.GetParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: UseCase) :
    ViewModel() {
    fun getHeroes() {
        viewModelScope.launch {
            useCase.performAction(GetParams(""))
                .collect {
                    Log.e("RESPONSE", "" + it.toString())
                }
        }
    }
}