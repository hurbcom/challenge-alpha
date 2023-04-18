package br.com.vaniala.starwars.ui.lastseen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vaniala.starwars.core.State
import br.com.vaniala.starwars.domain.model.LastSeen
import br.com.vaniala.starwars.domain.usecase.GetLastSeenFromBD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LastSeenViewModel @Inject constructor(
    private val getLastSeenFromBD: GetLastSeenFromBD,
) : ViewModel() {
    private val _lastSeen = MutableStateFlow<State<List<LastSeen>>>(State.Loading)
    val lastSeen: StateFlow<State<List<LastSeen>>>
        get() = _lastSeen

    fun lastSeen() = viewModelScope.launch {
        getLastSeenFromBD().collectLatest {
            _lastSeen.emit(it)
        }
    }
}
