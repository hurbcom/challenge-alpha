package com.edufelip.challengealpha.presentation.fragments.category_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edufelip.challengealpha.data.local.room.models.Favorite
import com.edufelip.challengealpha.domain.usecases.InsertFavoriteUseCase
import com.edufelip.challengealpha.presentation.base.models.StateUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailSharedViewModel @Inject constructor(
    private val insertFavoriteUseCase: InsertFavoriteUseCase
) : ViewModel() {

    private val _insertFavoriteState: MutableStateFlow<StateUI<Unit>> =
        MutableStateFlow(StateUI.Idle())
    val insertFavoriteState = _insertFavoriteState.asStateFlow()

    fun insertFavorite(favorite: Favorite) {
        viewModelScope.launch {
            insertFavoriteUseCase(favorite)
                .onStart {
                    _insertFavoriteState.emit(StateUI.Processing())
                }
                .catch {
                    _insertFavoriteState.emit(StateUI.Error())
                }
                .collect {
                    _insertFavoriteState.emit(StateUI.Processed(Unit))
                }
        }
    }
}