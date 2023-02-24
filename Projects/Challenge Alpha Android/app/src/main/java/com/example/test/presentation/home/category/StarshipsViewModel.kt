package com.example.test.presentation.home.category

import androidx.lifecycle.viewModelScope
import com.example.core.base.data.BaseResult
import com.example.core.base.presentation.BaseViewData
import com.example.core.base.presentation.BaseViewModel
import com.example.test.domain.usecases.ListGetParams
import com.example.test.domain.usecases.home.GetStarshipsUseCase
import com.example.test.presentation.models.CategoryItemDetailsViewData
import com.example.test.utils.Constants
import com.example.test.utils.Constants.FIRST_PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarshipsViewModel @Inject constructor(
    private val getStarshipsUseCase: GetStarshipsUseCase
) : BaseViewModel() {
    private var searchJob: Job? = null
    private var getDataJob: Job? = null
    private val _currentItems = MutableStateFlow<List<CategoryItemDetailsViewData>>(arrayListOf())
    private val _state = MutableStateFlow<BaseResult<List<BaseViewData>>>(BaseResult.Loading(true))
    override val state: StateFlow<BaseResult<List<BaseViewData>>> = _state
    private val _page = MutableStateFlow(FIRST_PAGE)

    override fun getListData(retry: Boolean, search: String?) {
        getDataJob?.cancel()
        getDataJob = viewModelScope.launch {
            var shouldShowLoading = false
            if (retry) _page.value = FIRST_PAGE
            if (_page.value == FIRST_PAGE) {
                shouldShowLoading = true
                _currentItems.value = arrayListOf()
            }
            _state.value = BaseResult.Loading(shouldShowLoading)

            getStarshipsUseCase.performAction(ListGetParams(_page.value, search)).collectLatest {
                if (it is BaseResult.Success) {
                    _currentItems.getAndUpdate { currentItems -> currentItems.plus(it.data) }
                    _state.value = it.copy(_currentItems.value, it.extraData)

                } else _state.value = it
            }
        }
    }

    override fun isLastPage(): Boolean {
        return (_state.value as? BaseResult.Success)?.run {
            extraData[Constants.NEXT_PAGE_KEY] == null
        } ?: false
    }

    override fun isLoading(): Boolean = _state.value is BaseResult.Loading
    override fun getNextPage() {
        _page.value = _page.value + 1
        getListData()
    }

    override fun searchDebounced(text: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            _page.value = 1
            getListData(search = text)
        }
    }
}