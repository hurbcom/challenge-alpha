package com.edufelip.challengealpha.presentation.fragments.general_list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edufelip.challengealpha.domain.models.GeneralListMenuItem
import com.edufelip.challengealpha.domain.usecases.GetGeneralListMenuItemsUseCase
import com.edufelip.challengealpha.presentation.base.models.StateUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralListMenuViewModel @Inject constructor(
    private val getGeneralListMenuItemsUseCase: GetGeneralListMenuItemsUseCase
) : ViewModel() {

    private val _generalListMenuItemsState: MutableStateFlow<StateUI<List<GeneralListMenuItem>>> =
        MutableStateFlow(StateUI.Idle())
    val generalListMenuItemsState = _generalListMenuItemsState.asStateFlow()

    fun getMenuList(context: Context) {
        viewModelScope.launch {
            getGeneralListMenuItemsUseCase(context)
                .onStart {
                    _generalListMenuItemsState.emit(StateUI.Processing())
                }
                .catch {
                    _generalListMenuItemsState.emit(StateUI.Error())
                }
                .collect {
                    _generalListMenuItemsState.emit(StateUI.Processed(it))
                }
        }
    }
}