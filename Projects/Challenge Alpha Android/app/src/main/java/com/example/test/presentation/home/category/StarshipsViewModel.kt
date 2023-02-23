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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarshipsViewModel @Inject constructor(
    private val getStarshipsUseCase: GetStarshipsUseCase
) : BaseViewModel() {
    private val currentItems = MutableStateFlow<List<CategoryItemDetailsViewData>>(arrayListOf())
    private val _state = MutableStateFlow<BaseResult<List<BaseViewData>>>(BaseResult.Loading)
    override val state: StateFlow<BaseResult<List<BaseViewData>>> = _state
    private val _page = MutableStateFlow(FIRST_PAGE)

    override fun getListData() {
        viewModelScope.launch {
            if (_page.value == FIRST_PAGE) _state.value = BaseResult.Loading
            getStarshipsUseCase.performAction(ListGetParams(_page.value)).collect {
                if (it is BaseResult.Success) {
                    currentItems.getAndUpdate { currentItems -> currentItems.plus(it.data) }
                    _state.value = it.copy(currentItems.value, it.extraData)

                } else _state.value = it
            }
        }
    }

    override fun isLastPage(): Boolean {
        return (_state.value as? BaseResult.Success)?.run {
            extraData[Constants.NEXT_PAGE_KEY] == null }
            ?: false
    }

    override fun isLoading(): Boolean = _state.value is BaseResult.Loading
    override fun getNextPage() {
        _page.value = _page.value + 1
        getListData()
    }
}