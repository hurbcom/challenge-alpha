package com.example.hurb_challenge.app.presentation.characterdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hurb_challenge.app.data.ConnectionFailure
import com.example.hurb_challenge.app.data.Failure
import com.example.hurb_challenge.app.data.Fetching
import com.example.hurb_challenge.app.data.HttpFailure
import com.example.hurb_challenge.app.data.Success
import com.example.hurb_challenge.app.domain.model.CardItem
import com.example.hurb_challenge.app.domain.model.Starship
import com.example.hurb_challenge.app.domain.model.Vehicle
import com.example.hurb_challenge.app.domain.toCardItem
import com.example.hurb_challenge.app.domain.usecase.GetCharacterStarshipsUseCase
import com.example.hurb_challenge.app.domain.usecase.GetCharacterVehiclesUseCase
import com.example.hurb_challenge.app.presentation.common.Empty
import com.example.hurb_challenge.app.presentation.common.Error
import com.example.hurb_challenge.app.presentation.common.Loaded
import com.example.hurb_challenge.app.presentation.common.Loading
import com.example.hurb_challenge.app.presentation.common.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getVehiclesUseCase: GetCharacterVehiclesUseCase,
    private val getStarshipsUseCase: GetCharacterStarshipsUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _characterVehiclesStateFlow: MutableStateFlow<State<List<CardItem<Vehicle>>>> =
        MutableStateFlow(Empty)

    private val _characterStarshipsStateFlow: MutableStateFlow<State<List<CardItem<Starship>>>> =
        MutableStateFlow(Empty)

    val characterStarshipsStateFlow = _characterStarshipsStateFlow.asStateFlow()
    val characterVehiclesStateFlow = _characterVehiclesStateFlow.asStateFlow()

    fun loadCharacterVehicles(urls: List<String>) {
        viewModelScope.launch(dispatcher) {
            getVehiclesUseCase.call(urls)
                .collect { result ->
                    when (result) {
                        is Fetching -> {
                            _characterVehiclesStateFlow.emit(Loading)
                        }
                        is Failure -> {
                            val message = when (result) {
                                is HttpFailure -> "Http error: ${result.errorCode}"
                                is ConnectionFailure -> "Connectivity Error"
                                else -> "Unknown error"
                            }
                            _characterVehiclesStateFlow.emit(Error(message))
                        }
                        is Success -> {
                            val vehicles = result.value.map { it.toCardItem() }
                            _characterVehiclesStateFlow.emit(Loaded(vehicles))
                        }
                    }
                }
        }
    }

    fun loadCharacterStarships(urls: List<String>) {
        viewModelScope.launch(dispatcher) {
            getStarshipsUseCase.call(urls)
                .collect { result ->
                    when (result) {
                        is Fetching -> {
                            _characterStarshipsStateFlow.emit(Loading)
                        }
                        is Failure -> {
                            val message = when (result) {
                                is HttpFailure -> "Http error: ${result.errorCode}"
                                is ConnectionFailure -> "Connectivity Error"
                                else -> "Unknown error"
                            }
                            _characterStarshipsStateFlow.emit(Error(message))
                        }
                        is Success -> {
                            val starships = result.value.map { it.toCardItem() }
                            _characterStarshipsStateFlow.emit(Loaded(starships))
                        }
                    }
                }
        }
    }
}
