package com.example.test.presentation.home.category

import androidx.lifecycle.viewModelScope
import com.example.core.base.data.BaseResult
import com.example.core.base.presentation.BaseViewData
import com.example.core.base.presentation.BaseViewModel
import com.example.test.domain.usecases.home.GetPeopleUseCase
import com.example.test.domain.usecases.ListGetParams
import com.example.test.presentation.models.CategoryItemDetailsViewData
import com.example.test.utils.Constants.FIRST_PAGE
import com.example.test.utils.Constants.NEXT_PAGE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val getPeopleUseCase: GetPeopleUseCase) :
    BaseViewModel() {
    private var searchJob: Job? = null
    private val _page = MutableStateFlow(FIRST_PAGE)
    private val _currentItems = MutableStateFlow<List<CategoryItemDetailsViewData>>(arrayListOf())
    private val _state = MutableStateFlow<BaseResult<List<BaseViewData>>>(BaseResult.Loading(true))
    override val state: StateFlow<BaseResult<List<BaseViewData>>> = _state

    override fun getListData(retry: Boolean, search: String?) {
        viewModelScope.launch {
            var shouldShowLoading = false
            if (retry) _page.value = FIRST_PAGE
            if (_page.value == FIRST_PAGE) {
                shouldShowLoading = true
                _currentItems.value = arrayListOf()
            }
            _state.value = BaseResult.Loading(shouldShowLoading)

            getPeopleUseCase.performAction(ListGetParams(_page.value, search)).collect {
                if (it is BaseResult.Success) {
                    _currentItems.getAndUpdate { currentItems -> currentItems.plus(it.data) }
                    _state.value = it.copy(_currentItems.value, it.extraData)

                } else _state.value = it
            }
        }
    }

    override fun isLastPage(): Boolean {
        return (_state.value as? BaseResult.Success)?.run { extraData[NEXT_PAGE_KEY] == null }
            ?: false
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