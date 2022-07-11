package com.edufelip.challengealpha.presentation.fragments.category_list.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edufelip.challengealpha.presentation.base.models.StateUI
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseCategoryListViewModel : ViewModel() {
    protected val hasNext = MutableStateFlow(false)
    protected val page = MutableStateFlow(1)
    protected var searchJob: Job? = null

    protected val _listItemState: MutableStateFlow<StateUI<Unit>> =
        MutableStateFlow(StateUI.Idle())
    val listItemState = _listItemState.asStateFlow()

    protected val _loadMoreState: MutableStateFlow<StateUI<Unit>> =
        MutableStateFlow(StateUI.Idle())
    val loadMoreState = _loadMoreState.asStateFlow()

    val lastText = MutableLiveData("")

    protected fun resetPageValue() {
        page.value = 1
    }

    protected fun increasePageValue() {
        val currentValue = page.value
        page.value = currentValue + 1
    }

    protected fun checkLoadMore(): Boolean {
        if (_listItemState.value.loading()) return false
        if (_loadMoreState.value.loading()) return false
        if (!hasNext.value) return false
        return true
    }

    abstract fun loadMore()
    abstract fun getItemList()
    abstract fun search(text: String)
}