package com.example.core.base.presentation

import androidx.lifecycle.ViewModel
import com.example.core.base.data.BaseResult
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {
    abstract val state: StateFlow<BaseResult<List<BaseViewData>>>

    abstract fun getListData()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    abstract fun getNextPage()
}