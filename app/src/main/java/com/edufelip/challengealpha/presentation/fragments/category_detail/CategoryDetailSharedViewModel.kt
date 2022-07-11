package com.edufelip.challengealpha.presentation.fragments.category_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edufelip.challengealpha.data.local.room.models.Favorite
import com.edufelip.challengealpha.domain.usecases.InsertFavoriteUseCase
import com.edufelip.challengealpha.presentation.base.models.Event
import com.edufelip.challengealpha.presentation.base.models.StateUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailSharedViewModel @Inject constructor(
    private val insertFavoriteUseCase: InsertFavoriteUseCase
) : ViewModel() {

    private val _insertFavoriteState: MutableSharedFlow<Event<StateUI<Unit>>> =
        MutableStateFlow(Event(StateUI.Idle()))
    val insertFavoriteState = _insertFavoriteState.asSharedFlow()

    fun insertFavorite(favorite: Favorite) {
        viewModelScope.launch {
            insertFavoriteUseCase(favorite)
                .onStart {
                    _insertFavoriteState.emit(Event(StateUI.Processing()))
                }
                .catch {
                    _insertFavoriteState.emit(Event(StateUI.Error()))
                }
                .collect {
                    _insertFavoriteState.emit(Event(StateUI.Processed(Unit)))
                }
        }
    }
}