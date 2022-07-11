package com.edufelip.challengealpha.presentation.fragments.category_list.category_list_vehicle

import androidx.lifecycle.viewModelScope
import com.edufelip.challengealpha.domain.models.Vehicle
import com.edufelip.challengealpha.domain.usecases.GetVehicleListUseCase
import com.edufelip.challengealpha.presentation.base.models.StateUI
import com.edufelip.challengealpha.presentation.fragments.category_list.base.BaseCategoryListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListVehicleViewModel @Inject constructor(
    private val getVehicleListUseCase: GetVehicleListUseCase
) : BaseCategoryListViewModel() {

    private val _vehicleList: MutableStateFlow<List<Vehicle>> =
        MutableStateFlow(emptyList())
    val vehicleList = _vehicleList.asStateFlow()

    override fun getItemList() {
        viewModelScope.launch {
            getVehicleListUseCase()
                .onStart {
                    _listItemState.emit(StateUI.Processing())
                }
                .catch { e ->
                    _listItemState.emit(StateUI.Error(e.toString()))
                }
                .collect {
                    _listItemState.emit(StateUI.Processed(Unit))
                    _vehicleList.emit(it.results)
                    hasNext.value = it.next != null
                    resetPageValue()
                    increasePageValue()
                }
        }
    }

    override fun loadMore() {
        if (!hasNext.value) return
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(400)
            getVehicleListUseCase(page = page.value)
                .onStart {
                    _listItemState.emit(StateUI.Processing())
                }
                .catch { e ->
                    _listItemState.emit(StateUI.Error(e.toString()))
                }
                .collect {
                    val list = mutableListOf<Vehicle>().apply {
                        addAll(_vehicleList.value)
                        addAll(it.results)
                    }
                    _listItemState.emit(StateUI.Processed(Unit))
                    _vehicleList.emit(list)
                    hasNext.value = it.next != null
                    increasePageValue()
                }
        }
    }

    override fun search(text: String) {
        if(text == lastText.value) return
        lastText.postValue(text)
        viewModelScope.launch {
            getVehicleListUseCase(search = text)
                .onStart {
                    _listItemState.emit(StateUI.Processing())
                }
                .catch { e ->
                    _listItemState.emit(StateUI.Error(e.toString()))
                }
                .collect {
                    _listItemState.emit(StateUI.Processed(Unit))
                    _vehicleList.emit(it.results)
                    hasNext.value = it.next != null
                    resetPageValue()
                }
        }
    }
}