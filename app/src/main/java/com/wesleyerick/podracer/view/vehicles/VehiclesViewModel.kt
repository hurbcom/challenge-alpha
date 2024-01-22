package com.wesleyerick.podracer.view.vehicles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wesleyerick.podracer.data.model.vehicles.Vehicle
import com.wesleyerick.podracer.data.repository.vehicles.IRepositoryVehicles
import com.wesleyerick.podracer.domain.usecase.vehicles.VehiclesUseCases
import com.wesleyerick.podracer.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehiclesViewModel(
    private val useCases: VehiclesUseCases,
) : ViewModel() {

    private val _getList: MutableLiveData<List<Vehicle>> =
        MutableLiveData(emptyList())
    val vehiclesList: LiveData<List<Vehicle>> get() = _getList

    private val _getVehicleDetails: MutableLiveData<Vehicle> =
        MutableLiveData(Vehicle())
    val vehicleDetails: LiveData<Vehicle> get() = _getVehicleDetails

    private var _onError: MutableLiveData<String> =
        MutableLiveData(String())
    val onError: LiveData<String> get() = _onError

    fun getList() = viewModelScope.launch(Dispatchers.IO) {
        when (
            val list = useCases.getList()
        ) {
            is Result.Success -> _getList.postValue(list.data.orEmpty())
            is Result.Failure -> onError(list.errorMessage)
        }
    }

    fun getDetails(id: String) = viewModelScope.launch(Dispatchers.IO) {
        when (
            val list = useCases.getDetails(id)
        ) {
            is Result.Success -> _getVehicleDetails.postValue(list.data ?: Vehicle())
            is Result.Failure -> onError(list.errorMessage)
        }
    }

    private fun onError(message: String) {
        _onError.postValue(message)
    }
}
