package com.edufelip.challengealpha.presentation.fragments.category_list.category_list_starship

import androidx.lifecycle.viewModelScope
import com.edufelip.challengealpha.domain.models.Film
import com.edufelip.challengealpha.domain.models.Starship
import com.edufelip.challengealpha.domain.usecases.GetStarshipListUseCase
import com.edufelip.challengealpha.presentation.base.models.Event
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
class CategoryListStarshipViewModel @Inject constructor(
    private val getStarshipListUseCase: GetStarshipListUseCase
) : BaseCategoryListViewModel() {

    private val _starshipList: MutableStateFlow<List<Starship>> =
        MutableStateFlow(emptyList())
    val starshipList = _starshipList.asStateFlow()

    override fun getItemList() {
        viewModelScope.launch {
            getStarshipListUseCase()
                .onStart {
                    _listItemState.emit(Event(StateUI.Processing()))
                }
                .catch { e ->
                    _listItemState.emit(Event(StateUI.Error(e.toString())))
                }
                .collect {
                    _listItemState.emit(Event(StateUI.Processed(Unit)))
                    _starshipList.emit(it.results)
                    hasNext.value = it.next != null
                    resetPageValue()
                    increasePageValue()
                }
        }
    }

    override fun loadMore() {
        if (!hasNext.value) return
        viewModelScope.launch {
            getStarshipListUseCase(page = page.value)
                .onStart {
                    _listItemState.emit(Event(StateUI.Processing()))
                }
                .catch { e ->
                    _listItemState.emit(Event(StateUI.Error(e.toString())))
                }
                .collect {
                    val list = mutableListOf<Starship>().apply {
                        addAll(_starshipList.value)
                        addAll(it.results)
                    }
                    _listItemState.emit(Event(StateUI.Processed(Unit)))
                    _starshipList.emit(list)
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
            getStarshipListUseCase(search = text)
                .onStart {
                    _listItemState.emit(Event(StateUI.Processing()))
                }
                .catch { e ->
                    _listItemState.emit(Event(StateUI.Error(e.toString())))
                }
                .collect {
                    _listItemState.emit(Event(StateUI.Processed(Unit)))
                    _starshipList.emit(it.results)
                    hasNext.value = it.next != null
                    resetPageValue()
                }
        }
    }
}