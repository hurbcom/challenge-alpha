package com.edufelip.challengealpha.presentation.fragments.category_list.category_list_specie

import androidx.lifecycle.viewModelScope
import com.edufelip.challengealpha.domain.models.Specie
import com.edufelip.challengealpha.domain.usecases.GetSpecieListUseCase
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
class CategoryListSpecieViewModel @Inject constructor(
    private val getSpecieListUseCase: GetSpecieListUseCase
) : BaseCategoryListViewModel() {

    private val _specieList: MutableStateFlow<List<Specie>> =
        MutableStateFlow(emptyList())
    val specieList = _specieList.asStateFlow()

    override fun getItemList() {
        viewModelScope.launch {
            getSpecieListUseCase()
                .onStart {
                    _listItemState.emit(StateUI.Processing())
                }
                .catch { e ->
                    _listItemState.emit(StateUI.Error(e.toString()))
                }
                .collect {
                    _listItemState.emit(StateUI.Processed(Unit))
                    _specieList.emit(it.results)
                    hasNext.value = it.next != null
                    resetPageValue()
                    increasePageValue()
                }
        }
    }

    override fun loadMore() {
        if (!hasNext.value) return
        viewModelScope.launch {
            getSpecieListUseCase(page = page.value)
                .onStart {
                    _listItemState.emit(StateUI.Processing())
                }
                .catch { e ->
                    _listItemState.emit(StateUI.Error(e.toString()))
                }
                .collect {
                    val list = mutableListOf<Specie>().apply {
                        addAll(_specieList.value)
                        addAll(it.results)
                    }
                    _listItemState.emit(StateUI.Processed(Unit))
                    _specieList.emit(list)
                    hasNext.value = it.next != null
                    increasePageValue()
                }
        }
    }

    override fun search(text: String) {
        if(text == lastText.value) return
        lastText.postValue(text)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(400)
            getSpecieListUseCase(search = text)
                .onStart {
                    _listItemState.emit(StateUI.Processing())
                }
                .catch { e ->
                    _listItemState.emit(StateUI.Error(e.toString()))
                }
                .collect {
                    _listItemState.emit(StateUI.Processed(Unit))
                    _specieList.emit(it.results)
                    hasNext.value = it.next != null
                    resetPageValue()
                }
        }
    }
}