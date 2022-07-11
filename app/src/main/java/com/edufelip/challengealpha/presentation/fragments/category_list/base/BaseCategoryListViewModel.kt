package com.edufelip.challengealpha.presentation.fragments.category_list.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edufelip.challengealpha.presentation.base.models.Event
import com.edufelip.challengealpha.presentation.base.models.StateUI
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseCategoryListViewModel : ViewModel() {
    protected val hasNext = MutableStateFlow(false)
    protected val page = MutableStateFlow(1)
    protected var searchJob: Job? = null

    protected val _listItemState: MutableSharedFlow<Event<StateUI<Unit>>> =
        MutableStateFlow(Event(StateUI.Idle()))
    val listItemState = _listItemState.asSharedFlow()

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
        if (_loadMoreState.value.loading()) return false
        if (!hasNext.value) return false
        return true
    }

    abstract fun loadMore()
    abstract fun getItemList()
    abstract fun search(text: String)
}