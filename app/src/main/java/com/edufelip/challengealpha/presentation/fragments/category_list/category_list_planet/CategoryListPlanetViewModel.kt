package com.edufelip.challengealpha.presentation.fragments.category_list.category_list_planet

import androidx.lifecycle.viewModelScope
import com.edufelip.challengealpha.domain.models.*
import com.edufelip.challengealpha.domain.usecases.*
import com.edufelip.challengealpha.presentation.base.models.StateUI
import com.edufelip.challengealpha.presentation.fragments.category_list.base.BaseCategoryListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListPlanetViewModel @Inject constructor(
    private val getPlanetListUseCase: GetPlanetListUseCase
) : BaseCategoryListViewModel() {

    private val _planetList: MutableStateFlow<List<Planet>> =
        MutableStateFlow(emptyList())
    val planetList = _planetList.asStateFlow()

    override fun getItemList() {
        viewModelScope.launch {
            getPlanetListUseCase()
                .onStart {
                    _listItemState.emit(StateUI.Processing())
                }
                .catch { e ->
                    _listItemState.emit(StateUI.Error(e.toString()))
                }
                .collect {
                    _listItemState.emit(StateUI.Processed(Unit))
                    _planetList.emit(it.results)
                    hasNext.value = it.next != null
                    resetPageValue()
                }
        }
    }

    override fun loadMore() {
        if (!hasNext.value) return
        viewModelScope.launch {
            getPlanetListUseCase(page = page.value!!)
                .onStart {
                    _listItemState.emit(StateUI.Processing())
                }
                .catch { e ->
                    _listItemState.emit(StateUI.Error(e.toString()))
                }
                .collect {
                    val list = mutableListOf<Planet>().apply {
                        addAll(_planetList.value)
                        addAll(it.results)
                    }
                    _listItemState.emit(StateUI.Processed(Unit))
                    _planetList.emit(list)
                    hasNext.value = it.next != null
                    page.value = page.value?.plus(1)
                }
        }
    }

    override fun search(text: String) {
        viewModelScope.launch {
            getPlanetListUseCase(search = text)
                .onStart {
                    _listItemState.emit(StateUI.Processing())
                }
                .catch { e ->
                    _listItemState.emit(StateUI.Error(e.toString()))
                }
                .collect {
                    _listItemState.emit(StateUI.Processed(Unit))
                    _planetList.emit(it.results)
                    hasNext.value = it.next != null
                    resetPageValue()
                }
        }
    }
}