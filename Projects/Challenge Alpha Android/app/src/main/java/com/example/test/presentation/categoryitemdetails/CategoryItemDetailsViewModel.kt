package com.example.test.presentation.categoryitemdetails

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.core.base.presentation.BaseExtensions.serializable
import com.example.test.presentation.models.CategoryItemDetailsViewData
import com.example.test.presentation.models.CategoryType
import com.example.test.utils.Constants.CATEGORY_PARAM_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CategoryItemDetailsViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(
        CategoryItemDetailsViewData(
            CategoryType.PEOPLE, "", "", "", "", "", ""
        )
    )
    val state: StateFlow<CategoryItemDetailsViewData> = _state

    fun handleArguments(bundle: Bundle?) {
        bundle?.run {
            serializable<CategoryItemDetailsViewData>(CATEGORY_PARAM_KEY)?.apply {
                _state.value = this
            }
        }
    }
}