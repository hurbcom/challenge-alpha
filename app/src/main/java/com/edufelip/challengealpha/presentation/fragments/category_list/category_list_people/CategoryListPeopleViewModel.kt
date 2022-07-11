package com.edufelip.challengealpha.presentation.fragments.category_list.category_list_people

import androidx.lifecycle.viewModelScope
import com.edufelip.challengealpha.domain.models.Film
import com.edufelip.challengealpha.domain.models.People
import com.edufelip.challengealpha.domain.usecases.GetPeopleListUseCase
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
class CategoryListPeopleViewModel @Inject constructor(
    private val getPeopleListUseCase: GetPeopleListUseCase
) : BaseCategoryListViewModel() {

    private val _peopleList: MutableStateFlow<List<People>> =
        MutableStateFlow(emptyList())
    val peopleList = _peopleList.asStateFlow()

    override fun getItemList() {
        viewModelScope.launch {
            getPeopleListUseCase()
                .onStart {
                    _listItemState.emit(Event(StateUI.Processing()))
                }
                .catch {
                    _listItemState.emit(Event(StateUI.Error()))
                }
                .collect {
                    _listItemState.emit(Event(StateUI.Processed(Unit)))
                    _peopleList.emit(it.results)
                    hasNext.value = it.next != null
                    resetPageValue()
                    increasePageValue()
                }
        }
    }

    override fun loadMore() {
        if(!checkLoadMore()) return
        viewModelScope.launch {
            getPeopleListUseCase(page = page.value)
                .onStart {
                    _listItemState.emit(Event(StateUI.Processing()))
                }
                .catch {
                    _listItemState.emit(Event(StateUI.Error()))
                }
                .collect {
                    val list = mutableListOf<People>().apply {
                        addAll(_peopleList.value)
                        addAll(it.results)
                    }
                    _listItemState.emit(Event(StateUI.Processed(Unit)))
                    _peopleList.emit(list)
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
            getPeopleListUseCase(search = text)
                .onStart {
                    _listItemState.emit(Event(StateUI.Processing()))
                }
                .catch { e ->
                    _listItemState.emit(Event(StateUI.Error(e.toString())))
                }
                .collect {
                    _listItemState.emit(Event(StateUI.Processed(Unit)))
                    _peopleList.emit(it.results)
                    hasNext.value = it.next != null
                    resetPageValue()
                }
        }
    }
}