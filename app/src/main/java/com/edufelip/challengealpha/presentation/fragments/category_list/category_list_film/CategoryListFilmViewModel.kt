package com.edufelip.challengealpha.presentation.fragments.category_list.category_list_film

import androidx.lifecycle.viewModelScope
import com.edufelip.challengealpha.domain.models.Film
import com.edufelip.challengealpha.domain.usecases.GetFilmListUseCase
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
class CategoryListFilmViewModel @Inject constructor(
    private val getFilmListUseCase: GetFilmListUseCase
) : BaseCategoryListViewModel() {

    private val _filmList: MutableStateFlow<List<Film>> =
        MutableStateFlow(emptyList())
    val filmList = _filmList.asStateFlow()

    override fun getItemList() {
        viewModelScope.launch {
            getFilmListUseCase()
                .onStart {
                    _listItemState.emit(StateUI.Processing())
                }
                .catch { e ->
                    _listItemState.emit(StateUI.Error(e.toString()))
                }
                .collect {
                    _listItemState.emit(StateUI.Processed(Unit))
                    _filmList.emit(it.results)
                    hasNext.value = it.next != null
                    resetPageValue()
                }
        }
    }

    override fun loadMore() {
        if (!hasNext.value) return
        viewModelScope.launch {
            getFilmListUseCase(page = page.value!!)
                .onStart {
                    _listItemState.emit(StateUI.Processing())
                }
                .catch { e ->
                    _listItemState.emit(StateUI.Error(e.toString()))
                }
                .collect {
                    val list = mutableListOf<Film>().apply {
                        addAll(_filmList.value)
                        addAll(it.results)
                    }
                    _listItemState.emit(StateUI.Processed(Unit))
                    _filmList.emit(list)
                    hasNext.value = it.next != null
                    page.value = page.value?.plus(1)
                }
        }
    }

    override fun search(text: String) {
        viewModelScope.launch {
            getFilmListUseCase(search = text)
                .onStart {
                    _listItemState.emit(StateUI.Processing())
                }
                .catch { e ->
                    _listItemState.emit(StateUI.Error(e.toString()))
                }
                .collect {
                    _listItemState.emit(StateUI.Processed(Unit))
                    _filmList.emit(it.results)
                    hasNext.value = it.next != null
                    resetPageValue()
                }
        }
    }
}