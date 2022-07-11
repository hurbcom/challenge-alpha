package com.edufelip.challengealpha.presentation.fragments.category_list.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edufelip.challengealpha.presentation.base.models.StateUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseCategoryListViewModel : ViewModel() {
    protected val hasNext = MutableStateFlow(false)
    protected val page = MutableLiveData(1)
    protected var searchJob: Job? = null

    protected val _listItemState: MutableStateFlow<StateUI<Unit>> =
        MutableStateFlow(StateUI.Idle())
    val listItemState = _listItemState.asStateFlow()
    val lastText = MutableLiveData("")

    protected fun resetPageValue() {
        page.postValue(1)
    }

    abstract fun getItemList()
    abstract fun loadMore()
    abstract fun search(text: String)
}